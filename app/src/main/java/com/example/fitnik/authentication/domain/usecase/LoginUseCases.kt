package com.example.fitnik.authentication.domain.usecase

data class LoginUseCases(
    val loginWithEmailUseCase: LoginWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val loginWithGoogleCredentialUseCase: LoginWithGoogleCredentialUseCase,
)
