package com.example.fitnik.authentication.presentation.login

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val credentialError: String? = null,
    val accIsCompleted: Boolean = false
)