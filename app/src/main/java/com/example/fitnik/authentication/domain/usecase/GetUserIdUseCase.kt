package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository

class GetUserIdUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(): String?  {
        return repository.getUserId()
    }

}