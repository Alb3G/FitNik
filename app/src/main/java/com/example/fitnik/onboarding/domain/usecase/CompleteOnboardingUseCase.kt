package com.example.fitnik.onboarding.domain.usecase

import com.example.fitnik.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {

    operator fun invoke() {
        repository.onboardingComplete()
    }

}