package com.example.fitnik.wod.presentation

import com.example.fitnik.core.domain.model.Wod


sealed interface WodEvent {
    object StartWod: WodEvent
    data class CompleteWod(val wod: Wod): WodEvent
    object NextPeriod: WodEvent
    object PreviousPeriod: WodEvent
}