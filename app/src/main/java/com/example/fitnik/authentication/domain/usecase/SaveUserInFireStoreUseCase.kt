package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.domain.model.User

class SaveUserInFireStoreUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user: User): Result<Unit> {
        return authRepository.saveUserToFirestore(user)
    }

}