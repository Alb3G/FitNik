package com.example.fitnik.authentication.presentation.setUpAccInfo

import android.content.Context
import java.time.LocalDate

sealed interface AccounEvent {
    data class CollectGender(val gender: String) : AccounEvent
    data class CollectActivity(val activity: String) : AccounEvent
    data class CollectObjective(val objective: String) : AccounEvent
    data class CollectBirthDate(val birthDate: LocalDate) : AccounEvent
    data class CollectWeight(val weight: String) : AccounEvent
    object ToggleWeightUnit : AccounEvent
    data class CollectHeight(val height: String) : AccounEvent
    object ToggleHeightUnit : AccounEvent
    data class Confirm(val context: Context) : AccounEvent
}