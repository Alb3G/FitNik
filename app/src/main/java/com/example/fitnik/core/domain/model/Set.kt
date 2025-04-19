package com.example.fitnik.core.domain.model

data class Set(
    val id: String = "",
    val setNumber: Int = 0,
    val weight: Double? = null,
    val reps: Int? = null,
    val completedReps: Int? = null,
    val rir: Int? = null,
    val percentage: Int? = null,
    val isCompleted: Boolean = false,
    val tempo: String? = null
)
