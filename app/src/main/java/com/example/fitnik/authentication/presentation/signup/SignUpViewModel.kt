package com.example.fitnik.authentication.presentation.signup

import androidx.lifecycle.ViewModel
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
                _state.value = _state.value.copy(email = event.email)
            }
            is SignUpEvent.FirstNameChange -> {
                _state.value = _state.value.copy(firsName = event.firstName)
            }
            is SignUpEvent.LastNameChange -> {
                _state.value = _state.value.copy(lastName = event.lastName)
            }
            is SignUpEvent.PasswordChange -> {
                _state.value = _state.value.copy(password = event.password)
            }
            is SignUpEvent.PrivacyConsentChante -> {
                _state.value = _state.value.copy(privacyConsent = event.isAccepted)
            }
            SignUpEvent.SignUp -> signUp()
        }
    }

    private fun signUp() {}

}