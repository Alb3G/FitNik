package com.example.fitnik.core.domain.model

import java.time.LocalDate

data class WodHistory(
    val date: LocalDate,
    val completed: Boolean
)