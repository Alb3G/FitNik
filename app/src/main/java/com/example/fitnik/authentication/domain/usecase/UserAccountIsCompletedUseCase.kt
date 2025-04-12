package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class UserAccountIsCompletedUseCase(
    private val authRepository: AuthRepository,
    private val localUserRepository: UserRepository,
    private val userPreferencesManager: UserPreferencesManager
) {
    // Checkeos con room para minimizar latencias.
    suspend operator fun invoke(): Boolean {
        val localUid = userPreferencesManager.getUserId().firstOrNull()
        if (localUid != null) {
            val localUser = localUserRepository.getUserById(localUid)
            return localUser?.accIscomplete == true
        }

        val remoteUid = authRepository.getUserId()
        if (remoteUid != null) {
            val remoteUser = authRepository.getUserObjFromFirestore(remoteUid).getOrNull()
            return remoteUser?.accIscomplete == true
        }

        return false
    }


}