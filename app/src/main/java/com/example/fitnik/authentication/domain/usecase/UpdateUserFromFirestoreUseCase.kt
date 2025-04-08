package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository

class UpdateUserFromFirestoreUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(uid: String, fields: Map<String, Any?>): Result<Unit> {
        return authRepository.updateUserFromFireStore(uid, fields)
    }

}