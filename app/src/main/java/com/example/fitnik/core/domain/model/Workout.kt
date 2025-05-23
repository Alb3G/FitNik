package com.example.fitnik.core.domain.model

import java.util.UUID

data class Workout(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val exercises: List<Exercise> = emptyList(),
    val isExpanded: Boolean = false
)