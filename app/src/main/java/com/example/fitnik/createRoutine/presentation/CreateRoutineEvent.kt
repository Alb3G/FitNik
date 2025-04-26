package com.example.fitnik.createRoutine.presentation

interface CreateRoutineEvent {
    data class RoutineNameChange(val name: String): CreateRoutineEvent
    data class WorkoutNameChange(val name: String): CreateRoutineEvent
    data class ExerciseNameChange(val name: String): CreateRoutineEvent
    data class WorkoutSetsChange(val sets: String): CreateRoutineEvent
    object AddWorkout: CreateRoutineEvent
    object AddExercise: CreateRoutineEvent
    object SaveRoutine: CreateRoutineEvent
}