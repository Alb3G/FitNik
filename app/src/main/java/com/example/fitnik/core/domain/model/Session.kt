package com.example.fitnik.core.domain.model

data class Session(
    val id: String = "",
    val number: Int = 1,
    val name: String = "",
    val sessionType: String = "",
    val feedback: String? = null,
    val effortLevel: Int? = null,
    val date: String? = null,
    val exercises: List<Exercise> = emptyList(), // Ejercicios que no están en grupos básicos
    val exerciseGroups: List<ExerciseGroup> = emptyList()
)