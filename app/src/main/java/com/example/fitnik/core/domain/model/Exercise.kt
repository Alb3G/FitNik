package com.example.fitnik.core.domain.model

data class Exercise(
    val name: String = "",
    val sets: Int = 0,
    val reps: String = "",
    val rir: String? = null,
    val restSeconds: Int? = null,
    val method: String? = null,
    val notes: String? = null,
    val loadPercentage: String? = null,
    val warmUp: Boolean = false
)
