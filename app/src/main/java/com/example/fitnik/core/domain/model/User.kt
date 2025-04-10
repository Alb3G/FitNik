package com.example.fitnik.core.domain.model

data class User(
    val uid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val gender: String = "",
    val activityLvl: String = "",
    val objective: String = "",
    val age: Int = 0,
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val workouts: List<Workout> = emptyList(),
    val accIscomplete: Boolean = false
)