package com.example.fitnik.onboarding.domain.usecase

import com.example.fitnik.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase(
    private val repository: OnboardingRepository
) {

    operator fun invoke(): Boolean {
        return repository.hasSeenOnboarding()
    }

}