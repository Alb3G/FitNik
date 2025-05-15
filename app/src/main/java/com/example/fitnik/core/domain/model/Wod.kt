package com.example.fitnik.core.domain.model

data class Wod(
    val name: String,
    val description: String,
    val rounds: Int,
    val durationMinutes: Int
)
