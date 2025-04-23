package com.example.fitnik.core.domain.model

import java.time.LocalDate

data class Workoutlog(
    val id: String,
    val bodyWeight: Double,
    val date: LocalDate,
    val workout: Workout
)
