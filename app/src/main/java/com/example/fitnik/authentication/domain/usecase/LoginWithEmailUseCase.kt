package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository

class LoginWithEmailUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }

}