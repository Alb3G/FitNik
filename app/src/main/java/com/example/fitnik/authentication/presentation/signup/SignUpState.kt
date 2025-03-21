package com.example.fitnik.authentication.presentation.signup

data class SignUpState(
    val firsName: String = "",
    val firstNameError: String = "",
    val lastName: String = "",
    val lastNameError: String = "",
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val privacyConsent: Boolean = false,
    val isLoading: Boolean = false,
    val hasTypedFirstName: Boolean = false,
    val hasTypedLastName: Boolean = false,
    val hasTypedEmail: Boolean = false,
    val hasTypedPassword: Boolean = false,
)
