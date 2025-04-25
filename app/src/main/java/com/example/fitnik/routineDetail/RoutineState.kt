package com.example.fitnik.routineDetail

import com.example.fitnik.core.domain.model.Workout

data class RoutineState(
    val workouts: List<Workout> = emptyList()
)
