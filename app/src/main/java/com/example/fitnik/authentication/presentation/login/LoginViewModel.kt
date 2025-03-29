package com.example.fitnik.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.model.PasswordValidationResult
import com.example.fitnik.utils.emailIsValid
import com.example.fitnik.utils.passIsValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is LoginEvent.PasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordError = null
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
        activateLoading()
        viewModelScope.launch {
            authRepository.login(state.value.email, state.value.password).onSuccess {
                _state.value = _state.value.copy(
                    isLoggedIn = true,
                    isLoading = false
                )
            }.onFailure {
                // Refactorizar los mensajes de error que muestro en la ui.
                val error = it.message
                _state.value = _state.value.copy(
                    emailError = error.toString(),
                    passwordError = error.toString(),
                    isLoading = false
                )
            }
        }
    }

    private fun activateLoading() {
        _state.value = _state.value.copy(isLoading = true)
    }
}