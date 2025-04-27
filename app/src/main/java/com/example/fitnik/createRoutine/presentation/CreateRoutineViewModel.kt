package com.example.fitnik.createRoutine.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import com.example.fitnik.routineDetail.data.repository.RoutineRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateRoutineViewModel @Inject constructor(
    private val repository: RoutineRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(CreateRoutineState())
    val state: StateFlow<CreateRoutineState> = _state.asStateFlow()

    // Para mantener un registro del workout actualmente seleccionado
    private var currentWorkoutIndex: Int = -1

    fun onEvent(event: CreateRoutineEvent) {
        when (event) {
            is CreateRoutineEvent.RoutineNameChange -> {
                _state.update {
                    it.copy(routineName = event.name)
                }
            }
            is CreateRoutineEvent.WorkoutNameChange -> {
                _state.update {
                    it.copy(workoutName = event.name)
                }
            }
            is CreateRoutineEvent.ExerciseNameChange -> {
                _state.update {
                    it.copy(exerciseName = event.name)
                }
            }
            is CreateRoutineEvent.WorkoutSetsChange -> {
                _state.update {
                    it.copy(workoutSets = event.sets)
                }
            }
            is CreateRoutineEvent.AddWorkout -> {
                if (_state.value.workoutName.isNotBlank()) {
                    _state.update { state ->
                        val newWorkout = Workout(
                            name = state.workoutName,
                            exercises = emptyList(),
                            isExpanded = true
                        )

                        val updatedWorkouts = state.workouts + newWorkout
                        // Establecer el índice del nuevo workout
                        currentWorkoutIndex = updatedWorkouts.size - 1

                        state.copy(
                            workouts = state.workouts + newWorkout,
                            workoutName = "" // Limpiar después de agregar
                        )
                    }
                }
            }
            is CreateRoutineEvent.AddExercise -> {
                if (_state.value.exerciseName.isNotBlank() && _state.value.workoutSets.isNotBlank() && currentWorkoutIndex >= 0) {
                    _state.update { currentState ->
                        val currentWorkouts = currentState.workouts.toMutableList()
                        val currentWorkout = currentWorkouts[currentWorkoutIndex]

                        val newExercise = Exercise(
                            id = UUID.randomUUID().toString(),
                            name = _state.value.exerciseName,
                            sets = List(_state.value.workoutSets.toIntOrNull() ?: 1) {
                                WorkoutSet(
                                    id = UUID.randomUUID().toString(),
                                    weight = 0.0,
                                    reps = 0
                                )
                            }
                        )

                        val updatedWorkout = currentWorkout.copy(
                            exercises = currentWorkout.exercises + newExercise
                        )

                        currentWorkouts[currentWorkoutIndex] = updatedWorkout

                        currentState.copy(
                            workouts = currentWorkouts,
                            exerciseName = "", // Limpiar después de agregar
                            workoutSets = ""   // Limpiar después de agregar
                        )
                    }
                }
            }
            is CreateRoutineEvent.SaveRoutine -> {
                if (
                    _state.value.routineName.isNotBlank() && _state.value.workouts.isNotEmpty()
                ) {
                    saveRoutine()
                }
            }
        }
    }

    /**
     * @param index Indice para determinar que workout estamos modificando.
     */
    fun setCurrentWorkoutIndex(index: Int) {
        currentWorkoutIndex = index
    }

    fun saveRoutine() {
        viewModelScope.launch {
            val routineId = UUID.randomUUID().toString()
            val routine = Routine(
                id = routineId,
                name = _state.value.routineName,
                workouts = _state.value.workouts.map { workoutItem ->
                    Workout(
                        id = UUID.randomUUID().toString(),
                        name = workoutItem.name,
                        exercises = workoutItem.exercises.map { exerciseItem ->
                            Exercise(
                                id = UUID.randomUUID().toString(),
                                name = exerciseItem.name,
                                sets = List(exerciseItem.sets.size) {
                                    WorkoutSet(
                                        id = UUID.randomUUID().toString(),
                                        weight = 0.0,
                                        reps = 0
                                    )
                                }
                            )
                        }
                    )
                }
            )
            repository.createRoutine(routine)
            delay(100)
        }
    }

    fun updateExerciseFields(name: String, sets: String) {
        _state.update {
            it.copy(
                exerciseName = name,
                workoutSets = sets
            )
        }
    }
}