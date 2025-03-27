package com.example.fitnik.authentication.model

data class PasswordValidationResult(
    val metMinLength: Boolean = false,
    val metUpperCase: Boolean = false,
    val metNumber: Boolean = false,
    val metSpecialChar: Boolean = false
) {
    val metAllRequirements = metMinLength && metUpperCase && metNumber && metSpecialChar
}