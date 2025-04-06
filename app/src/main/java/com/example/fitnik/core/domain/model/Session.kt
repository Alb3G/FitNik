package com.example.fitnik.core.domain.model

data class Session(
    val number: Int = 1,
    val warmUp: List<Exercise> = emptyList()
)