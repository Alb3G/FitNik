package com.example.fitnik.authentication.presentation.setUpAccInfo

import java.time.LocalDate

data class SetUpAccState(
    val gender: String = "",
    val activity: String = "",
    val objective: String = "Gain Muscle",
    var birthDate: LocalDate = LocalDate.now(),
    val weight: String = "",
    val height: String = "",
    val isWeightInKg: Boolean = true,
    val isHeightInCm: Boolean = true,
    val accIsComplete: Boolean = false
)