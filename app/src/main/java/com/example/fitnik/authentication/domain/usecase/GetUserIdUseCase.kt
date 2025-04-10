package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.core.data.preferences.UserPreferencesManager
import kotlinx.coroutines.flow.first

class GetUserIdUseCase(
    private val userPreferencesManager: UserPreferencesManager
) {

    suspend operator fun invoke(): String?  {
        return userPreferencesManager.getUserId().first()
    }

}