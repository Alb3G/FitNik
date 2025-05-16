package com.example.fitnik.core.domain.model

import java.time.LocalDate

data class WodHistory(
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false
)