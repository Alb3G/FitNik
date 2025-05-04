package com.example.fitnik.routineDetail.presentation

import com.example.fitnik.core.domain.model.Workout

data class RoutineState(
    val workouts: List<Workout> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
