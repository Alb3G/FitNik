package com.example.fitnik.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.fitnik.utils.emailIsValid
import com.example.fitnik.utils.nameIsValid
import com.example.fitnik.utils.passwordIsValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state


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
            is SignUpEvent.PrivacyConsentChante -> {
                _state.value = _state.value.copy(privacyConsent = event.isAccepted)
            }
            SignUpEvent.SignUp -> signUp()
        }
    }

    private fun signUp() {}

    fun validateName(name: String): Boolean {
        val isValid = nameIsValid(name)
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
}