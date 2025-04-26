package com.example.fitnik.authentication.presentation.signup

/**
 * El enfocar la logica de la ui con eventos nos va a hacer tener
 * un codigo mas escalable que gestionar en el viewModel cada uno
 * de los cambios de los textFields y otras acciones que el usuario
 * pueda realizar en la vista.
 */
sealed interface SignUpEvent {

    data class FirstNameChange(val firstName: String): SignUpEvent

    data class LastNameChange(val lastName: String): SignUpEvent

    data class EmailChange(val email: String): SignUpEvent

    data class PasswordChange(val password: String): SignUpEvent

    data class PrivacyConsentChange(val isAccepted: Boolean): SignUpEvent

    object SignUp: SignUpEvent

}