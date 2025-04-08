package com.example.fitnik.authentication.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.usecase.LoginUseCases
import com.example.fitnik.authentication.domain.usecase.UserAccountIsCompletedUseCase
import com.example.fitnik.authentication.model.PasswordValidationResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
    private val userAccountIsCompletedUseCase: UserAccountIsCompletedUseCase
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
                updateLoggedInState()
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

    fun loginWithGoogle(context: Context) {
        val credentialManager = CredentialManager.create(context)
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("519350555394-d9f0emlj4sp0bgq1kqghb4335k8um5bt.apps.googleusercontent.com")
            .setNonce(hashedNonce)
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(context, request)
                val credential = result.credential
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val googleIdToken = googleIdTokenCredential.idToken
                loginUseCases.loginWithGoogleCredentialUseCase(googleIdToken).onSuccess { user ->
                    updateLoggedInState()
                    Toast.makeText(context, "Welcome ${user.displayName}", Toast.LENGTH_SHORT).show()
                }.onFailure {
                    Toast.makeText(context, "Something went wrong there.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
            } catch (e: GetCredentialException) {
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setError(message: String) {
        _state.value = _state.value.copy(
            credentialError = message
        )
    }

    private fun activateLoading() {
        _state.value = _state.value.copy(isLoading = true)
    }

    private fun updateLoggedInState() {
        viewModelScope.launch {
            val isCompleted = userAccountIsCompletedUseCase()
            _state.value = _state.value.copy(
                isLoggedIn = true,
                accIsCompleted = isCompleted,
                isLoading = false
            )
        }
    }

}