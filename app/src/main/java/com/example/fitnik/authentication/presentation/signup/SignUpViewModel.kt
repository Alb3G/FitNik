package com.example.fitnik.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.SignUpUseCases
import com.example.fitnik.authentication.model.PasswordValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val signUpUseCases: SignUpUseCases
): ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    val signUpAllowed: Boolean get() =
        state.value.privacyConsent &&
        validateName(state.value.firsName) &&
        validateName(state.value.lastName) &&
        validateEmail(state.value.email) &&
        validatePassword(state.value.password).metAllRequirements


    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChange -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    hasTypedEmail = true
                )
            }
            is SignUpEvent.FirstNameChange -> {
                _state.value = _state.value.copy(
                    firsName = event.firstName,
                    hasTypedFirstName = true
                )
            }
            is SignUpEvent.LastNameChange -> {
                _state.value = _state.value.copy(
                    lastName = event.lastName,
                    hasTypedLastName = true
                )
            }
            is SignUpEvent.PasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    hasTypedPassword = true
                )
            }
            is SignUpEvent.PrivacyConsentChange -> {
                _state.value = _state.value.copy(privacyConsent = event.isAccepted)
            }
            SignUpEvent.SignUp -> signUp()
        }
    }

    fun signUp() {
        activateLoading()
        viewModelScope.launch {
            signUpUseCases.signUpUseCase(_state.value.email, _state.value.password).onSuccess {
                _state.value = _state.value.copy(
                    isSignedUp = true,
                    isLoading = false
                )
            }.onFailure {
                val error = it.message
                _state.value = _state.value.copy(
                    emailError = error.toString(),
                    isLoading = false
                )
            }
        }
    }

    private fun activateLoading() {
        _state.value = _state.value.copy(isLoading = true)
    }

    fun validateName(name: String): Boolean {
        val isValid = signUpUseCases.validateNameUseCase(name)
        if (!isValid) {
            _state.value = _state.value.copy(
                firstNameError = "Invalid Name format",
                lastNameError = "Invalid Name format"
            )
            return false
        }
        return true
    }

    fun validateEmail(email: String): Boolean {
        val isValid = signUpUseCases.validateEmailUseCase(email)
        if (!isValid) {
            _state.value = _state.value.copy(
                emailError = "Invalid email format"
            )
            return false
        }
        return true
    }

    fun validatePassword(password: String): PasswordValidationResult {
        return signUpUseCases.validatePasswordUseCase(password)
    }
}