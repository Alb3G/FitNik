package com.example.fitnik.authentication.presentation.signup

data class SignUpState(
    val firsName: String = "",
    val lastName: String = "",
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val privacyConsent: Boolean = false,
    val isLoading: Boolean = false
)
