package com.example.fitnik.core.domain.model

import java.util.UUID

data class Workout(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val type: String = "",
    val userId: String = "",
    val sessions: List<Session> = emptyList()
)
