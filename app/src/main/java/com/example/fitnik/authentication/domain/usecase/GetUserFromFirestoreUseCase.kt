package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.domain.model.User

class GetUserFromFirestoreUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(uid: String): User? {
        return authRepository.getUserObjFromFirestore(uid).getOrNull()
    }

}