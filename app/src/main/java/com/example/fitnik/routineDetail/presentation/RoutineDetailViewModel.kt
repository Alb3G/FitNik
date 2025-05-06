package com.example.fitnik.routineDetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        weight: Float?,
        reps: Int?
    ) {

    }

    fun toggleWorkoutIsExpanded(workoutId: String) {
        val updatedWorkouts = _state.value.workouts.map { workout ->
            if (workoutId === workout.id) {
                workout.copy(isExpanded = !workout.isExpanded)
            } else {
                workout
            }
        }

        _state.update {
            it.copy(workouts = updatedWorkouts)
        }
    }
}