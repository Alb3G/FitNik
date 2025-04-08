package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository

class UserAccountIsCompletedUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Boolean {
        val uid = authRepository.getUserId() ?: return false
        return authRepository.getUserObjFromFirestore(uid).getOrNull()?.accIscomplete == true
    }

}