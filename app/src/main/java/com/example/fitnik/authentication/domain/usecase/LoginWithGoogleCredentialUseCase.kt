package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class LoginWithGoogleCredentialUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(idToken: String): Result<FirebaseUser> {
        return repository.loginWithCredential(idToken)
    }

}