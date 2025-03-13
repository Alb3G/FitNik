package com.example.fitnik.onboarding.model

import androidx.annotation.DrawableRes


data class OnboardingPagerModel(
    val title: String,
    val body: String,
    @DrawableRes val image: Int,
)
