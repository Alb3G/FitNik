package com.example.fitnik.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.LoginUseCases
import com.example.fitnik.authentication.model.PasswordValidationResult
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val loginUseCases: LoginUseCases
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
        val isValid = loginUseCases.validateEmailUseCase(email)
        if (!isValid) {
            _state.value = _state.value.copy(
                emailError = "Invalid email format"
            )
            return false
        }
        return true
    }

    fun validatePassword(password: String): PasswordValidationResult {
        return loginUseCases.validatePasswordUseCase(password)
    }

    fun login() {
        activateLoading()
        viewModelScope.launch {
            loginUseCases.loginWithEmailUseCase(state.value.email, state.value.password).onSuccess {
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

    fun loginWithGoogle(credential: AuthCredential) {
//        viewModelScope.launch {
//            loginUseCases.loginWithGoogleCredentialUseCase()
//        }
    }

    fun setError(message: String) {
        _state.value = _state.value.copy(
            credentialError = message
        )
    }

    private fun activateLoading() {
        _state.value = _state.value.copy(isLoading = true)
    }
}