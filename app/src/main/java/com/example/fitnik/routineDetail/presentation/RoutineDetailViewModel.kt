package com.example.fitnik.routineDetail.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.core.domain.model.WorkoutSet
import com.example.fitnik.home.domain.usecase.GetRoutinesUseCase
import com.example.fitnik.routineDetail.domain.RoutineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineDetailViewModel @Inject constructor(
    private val getRoutinesUseCase: GetRoutinesUseCase,
    private val routineRepository: RoutineRepository
): ViewModel() {

    private val _state = MutableStateFlow(RoutineState())
    val state: StateFlow<RoutineState> = _state.asStateFlow()

    fun loadWorkouts(routineId: String) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }

                routineRepository.getAllRoutineWorkouts(routineId)
                    .catch { exception ->
                        _state.update { it.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error occurred"
                        )}
                    }
                    .collect { workouts ->
                        _state.update {
                            it.copy(
                                workouts = workouts,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error occurred"
                )}
            }
        }
    }

    fun updateWorkoutSet(
        workoutId: String,
        exerciseId: String,
        setIndex: Int,
        weight: Double?,
        reps: Int?,
    ) {
        _state.update { current ->
            current.copy(
                workouts = current.workouts.map { workout ->
                    if (workout.id == workoutId) {
                        workout.copy(
                            exercises = workout.exercises.map { exercise ->
                                if (exercise.id == exerciseId) {
                                    exercise.copy(
                                        sets = exercise.sets.mapIndexed { idx, set ->
                                            if (idx == setIndex) set.copy(weight = weight, reps = reps)
                                            else set
                                        }
                                    )
                                } else exercise
                            }
                        )
                    } else workout
                }
            )
        }
    }

    fun updateWorkoutSets(
        exerciseId: String,
        updatedSets: List<WorkoutSet>,
        context: Context
    ) {
        viewModelScope.launch {
            try {
                routineRepository.updateSetsForExercise(exerciseId, updatedSets)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message ?: "Failed to update sets") }
            }

            Toast.makeText(context, "Progress saved succesfully", Toast.LENGTH_SHORT).show()
        }
    }


    fun toggleWorkoutIsExpanded(workoutId: String) {
        _state.update { currentState ->
            val updatedWorkouts = currentState.workouts.map { workout ->
                if (workout.id == workoutId) {
                    workout.copy(isExpanded = !workout.isExpanded)
                } else {
                    workout
                }
            }
            currentState.copy(workouts = updatedWorkouts)
        }
    }
}