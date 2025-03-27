package com.example.fitnik.utils

import com.example.fitnik.authentication.model.PasswordValidationResult
import kotlin.text.Regex

fun nameIsValid(name: String): Boolean {
    val regex = Regex("^[a-zA-ZÀ-ÿ\\s]{1,150}\$")
    return name.matches(regex)
}

fun emailIsValid(email: String): Boolean {
    val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return regex.matches(email)
}

fun passIsValid(password: String): PasswordValidationResult {
    return PasswordValidationResult(
        metMinLength = password.length >= 8,
        metUpperCase = password.any { it.isUpperCase() },
        metNumber = password.any { it.isDigit() },
        metSpecialChar = password.any { !it.isLetterOrDigit() }
    )
}
