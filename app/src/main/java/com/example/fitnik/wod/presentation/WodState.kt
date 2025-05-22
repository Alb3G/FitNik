package com.example.fitnik.wod.presentation

import com.example.fitnik.core.domain.model.Wod
import com.example.fitnik.core.domain.model.WodHistory
import java.time.LocalDate

data class WodState(
    val currentWod: Wod? = null,
    val streak: Int = 0,
    val history: List<WodHistory> = emptyList(),
    val historyStartDate: LocalDate = LocalDate.now(),
    val isCompleted: Boolean = false,
    val remainingSeconds: Int = 0,
    val isWorkoutActive: Boolean = false
)