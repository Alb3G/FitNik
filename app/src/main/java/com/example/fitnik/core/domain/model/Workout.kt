package com.example.fitnik.core.domain.model

data class Workout(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val userId: String = "",
    val description: String? = null,
    val dayOfWeek: String? = null,
    val sessions: List<Session> = emptyList()
)