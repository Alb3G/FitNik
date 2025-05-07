package com.example.fitnik.core.domain.model

import java.util.UUID

data class WorkoutSet(
    val id: String = UUID.randomUUID().toString(),
    val weight: Double? = null,
    val reps: Int? = null,
    val isCompleted: Boolean = false
)