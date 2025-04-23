package com.example.fitnik.core.domain.model

data class WorkoutSet(
    val id: String = "",
    val weight: Double? = null,
    val reps: Int? = null,
    val isCompleted: Boolean = false
)