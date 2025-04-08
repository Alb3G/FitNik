package com.example.fitnik.core.domain.model

data class Session(
    val number: Int = 1,
    val exercises: List<Exercise> = emptyList()
)