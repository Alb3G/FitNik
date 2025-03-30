package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.model.PasswordValidationResult

class ValidatePasswordUseCase {

    operator fun invoke(password: String): PasswordValidationResult {
        return PasswordValidationResult(
            metMinLength = password.length >= 8,
            metUpperCase = password.any { it.isUpperCase() },
            metNumber = password.any { it.isDigit() },
            metSpecialChar = password.any { !it.isLetterOrDigit() }
        )
    }

}