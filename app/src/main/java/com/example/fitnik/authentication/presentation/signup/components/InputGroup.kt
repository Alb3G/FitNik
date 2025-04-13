package com.example.fitnik.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.authentication.model.TextFieldConfig
import com.example.fitnik.authentication.presentation.components.AuthTextField
import com.example.fitnik.authentication.presentation.signup.SignUpEvent
import com.example.fitnik.authentication.presentation.signup.SignUpViewModel

@Composable
fun InputGroup(
    viewModel: SignUpViewModel,
) {
    val state by viewModel.state.collectAsState()

    // Focus manager nos permite determinar el foco del input y marcar un orden
    // a seguir cuando clickamos en la tecla done del keyboard.
    val focusManager = LocalFocusManager.current

    val firstNameConfig = TextFieldConfig(
        label = "First Name",
        leadingIcon = R.drawable.profile,
        contentDescription = "Write down your name",
        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
        isError = !viewModel.validateName(state.firsName) && state.hasTypedFirstName
    )
    val lastNameConfig = TextFieldConfig(
        label = "Last Name",
        leadingIcon = R.drawable.profile,
        contentDescription = "Profile icon",
        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
        isError = !viewModel.validateName(state.lastName) && state.hasTypedLastName
    )
    val emailConfig = TextFieldConfig(
        label = "Email",
        leadingIcon = R.drawable.message,
        contentDescription = "Email icon",
        isEmail = true,
        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
        isError = !viewModel.validateEmail(state.email) && state.hasTypedEmail
    )
    val passwordConfig = TextFieldConfig(
        label = "Password",
        leadingIcon = R.drawable.lock,
        contentDescription = "Password icon",
        isPassword = true,
        keyboardActions = KeyboardActions { focusManager.clearFocus() },
    )

    AuthTextField(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 64.dp),
        tfValue = state.firsName,
        textFieldConfig = firstNameConfig,
        errorMessage = state.firstNameError.takeIf { state.hasTypedFirstName }
    ) { viewModel.onEvent(SignUpEvent.FirstNameChange(it)) }

    Spacer(modifier = Modifier.height(8.dp))

    AuthTextField(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 64.dp),
        tfValue = state.lastName,
        textFieldConfig = lastNameConfig,
        errorMessage = state.lastNameError.takeIf { state.hasTypedLastName }
    ) { viewModel.onEvent(SignUpEvent.LastNameChange(it)) }

    Spacer(modifier = Modifier.height(8.dp))

    AuthTextField(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 64.dp),
        tfValue = state.email,
        textFieldConfig = emailConfig,
        errorMessage = state.emailError.takeIf { state.hasTypedEmail }
    ) { viewModel.onEvent(SignUpEvent.EmailChange(it)) }

    Spacer(modifier = Modifier.height(8.dp))

    AuthTextField(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 64.dp),
        tfValue = state.password,
        textFieldConfig = passwordConfig,
    ) { viewModel.onEvent(SignUpEvent.PasswordChange(it)) }
}