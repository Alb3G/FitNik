package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.domain.repository.UserRepository

class UserAccountIsCompletedUseCase(
    private val authRepository: AuthRepository,
    private val localUserRepository: UserRepository,
) {
    // Checkeos con room para minimizar latencias.
    suspend operator fun invoke(): Boolean {
        val uid = authRepository.getUserId() ?: return false
        val localUser = localUserRepository.getUserById(uid)
        return localUser?.accIscomplete == true
    }

}