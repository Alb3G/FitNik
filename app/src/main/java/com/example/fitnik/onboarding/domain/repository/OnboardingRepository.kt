package com.example.fitnik.onboarding.domain.repository

interface OnboardingRepository {

    fun hasSeenOnboarding(): Boolean

    fun onboardingComplete()

}