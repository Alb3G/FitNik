package com.example.fitnik.createRoutine.presentation

import com.example.fitnik.core.domain.model.Workout

data class CreateRoutineState(
    val routineName: String = "",
    val workoutName: String = "",
    val exerciseName: String = "",
    val workoutSets: String = "",
    val workouts: List<Workout> = emptyList()
)
