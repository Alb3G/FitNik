package com.example.fitnik.core.domain.model

data class Exercise(
    val name: String = "",
    val sets: List<WorkoutSet> = emptyList(),
)