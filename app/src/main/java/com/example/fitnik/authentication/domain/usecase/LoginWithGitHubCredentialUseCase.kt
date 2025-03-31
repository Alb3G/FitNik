package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.repository.AuthRepository

class LoginWithGitHubCredentialUseCase(
    private val repository: AuthRepository
) {

//    suspend operator fun invoke(credential: AuthCredential): Result<FirebaseUser> {
//        return repository.loginWithCredential(credential)
//    }

}