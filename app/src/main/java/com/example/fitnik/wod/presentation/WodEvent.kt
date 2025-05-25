package com.example.fitnik.wod.presentation


sealed interface WodEvent {
    object CompleteWod : WodEvent
    object NextPeriod: WodEvent
    object PreviousPeriod: WodEvent
}