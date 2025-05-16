package com.example.fitnik.core.domain.model

data class Wod(
    val name: String = "",
    val description: String = "",
    val rounds: Int = 1,
    val durationMinutes: Int = 5,
    val isCompleted: Boolean = false
)
