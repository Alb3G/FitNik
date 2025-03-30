package com.example.fitnik.authentication.domain.usecase

data class SignUpUseCases(
    val validateNameUseCase: ValidateNameUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val signUpUseCase: SignUpUseCase
)
