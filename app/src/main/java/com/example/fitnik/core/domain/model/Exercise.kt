package com.example.fitnik.core.domain.model

data class Exercise(
    val id: String = "",
    val name: String = "",
    val method: String? = null,
    val notes: String? = null,
    val isWarmUp: Boolean = false,
    val restSeconds: Int? = null,
    val targetSets: Int = 0,
    val rm: Int? = null,
    val sets: List<Set> = emptyList()
)
