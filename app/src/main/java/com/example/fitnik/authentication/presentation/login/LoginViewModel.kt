package com.example.fitnik.authentication.presentation.login

import androidx.lifecycle.ViewModel
import com.example.fitnik.authentication.presentation.signup.SignUpState
import com.example.fitnik.utils.emailIsValid
import com.example.fitnik.utils.passwordIsValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    hasTypedEmail = true
                )
            }
            is LoginEvent.PasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    hasTypedPassword = true
                )
            }
            LoginEvent.Login -> login()
        }
    }

    fun validateEmail(email: String): Boolean {
        val isValid = emailIsValid(email)
        if (!isValid) {
            _state.value = _state.value.copy(
                emailError = "Invalid email format"
            )
            return false
        }
        return true
    }

    fun validatePassword(password: String): Boolean {
        val isValid = passwordIsValid(password)
        if (!isValid) {
            _state.value = _state.value.copy(
                passwordError = "Remember to have 1Mayus, 1Number and 1Special character and minimum 8 characters."
            )
            return false
        }
        return true
    }

    private fun login() {}
}