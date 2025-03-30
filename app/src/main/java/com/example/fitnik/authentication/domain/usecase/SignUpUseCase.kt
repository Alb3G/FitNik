package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {

     suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signUp(email, password)
    }

}