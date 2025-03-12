package com.example.fitnik.onboarding.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp

data class OnboardingPagerModel(
    val title: String,
    val body: String,
    @DrawableRes val image: Int,
    val imageHeight: Dp
)
