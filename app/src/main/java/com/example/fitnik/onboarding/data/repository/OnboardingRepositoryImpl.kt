package com.example.fitnik.onboarding.data.repository

import android.content.SharedPreferences
import com.example.fitnik.onboarding.domain.repository.OnboardingRepository
import androidx.core.content.edit

class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferences
): OnboardingRepository {

    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override fun onboardingComplete() {
        sharedPreferences.edit { putBoolean(HAS_SEEN_ONBOARDING, true) }
    }
}