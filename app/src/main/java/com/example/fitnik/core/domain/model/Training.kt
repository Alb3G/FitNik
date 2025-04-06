package com.example.fitnik.core.domain.model

data class Training(
    val name: String = "",
    val type: String = "",
    val sessions: List<Session> = emptyList()
)
