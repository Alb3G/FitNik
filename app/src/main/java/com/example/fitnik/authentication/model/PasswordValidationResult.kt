package com.example.fitnik.authentication.model

data class PasswordValidationResult(
    val metMinLength: Boolean = false,
    val metUpperCase: Boolean = false,
    val metNumber: Boolean = false,
    val metSpecialChar: Boolean = false
) {
    val metAllRequirements = metMinLength && metUpperCase && metNumber && metSpecialChar
}

fun passIsValid(password: String): PasswordValidationResult {
    return PasswordValidationResult(
        metMinLength = password.length >= 8,
        metUpperCase = password.any { it.isUpperCase() },
        metNumber = password.any { it.isDigit() },
        metSpecialChar = password.any { !it.isLetterOrDigit() }
    )
}