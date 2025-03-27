package com.example.fitnik.authentication.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.model.PasswordValidationResult
import com.example.fitnik.utils.passIsValid
import com.example.fitnik.authentication.presentation.signup.SignUpState
import com.example.fitnik.utils.emailIsValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

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

    fun validatePassword(password: String): PasswordValidationResult {
        return passIsValid(password)
    }

    fun login() {
        viewModelScope.launch {
            authRepository.login(state.value.email, state.value.password).onSuccess {
                Log.i("Login Process", "SUCCESS")
            }.onFailure {
                val error = it.message
                Log.i("Login Process", error.toString())
            }
        }
    }
}