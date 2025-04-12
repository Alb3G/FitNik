package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import kotlinx.coroutines.flow.firstOrNull

class GetUserIdUseCase(
    private val authRepository: AuthRepository,
    private val userPreferencesManager: UserPreferencesManager
) {

    suspend operator fun invoke(): String?  {
        val localUid = userPreferencesManager.getUserId().firstOrNull()
        if (localUid != null)
            return localUid

        val remoteUid = authRepository.getUserId()
        if (remoteUid != null)
            return remoteUid

        return null
    }

}