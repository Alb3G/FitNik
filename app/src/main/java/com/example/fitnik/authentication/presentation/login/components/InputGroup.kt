package com.example.fitnik.authentication.presentation.login.components

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
import com.example.fitnik.authentication.presentation.login.LoginEvent
import com.example.fitnik.authentication.presentation.login.LoginViewModel

@Composable
fun InputGroup(
    viewModel: LoginViewModel,
) {
    val state by viewModel.state.collectAsState()

    // Focus manager nos permite determinar el foco del input y marcar un orden
    // a seguir cuando clickamos en la tecla done del keyboard.
    val focusManager = LocalFocusManager.current

    val emailConfig = TextFieldConfig(
        label = "Email",
        leadingIcon = R.drawable.message,
        contentDescription = "Email icon",
        isEmail = true,
        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
        isError = state.emailError != null
    )
    val passwordConfig = TextFieldConfig(
        label = "Password",
        leadingIcon = R.drawable.lock,
        contentDescription = "Password icon",
        isPassword = true,
        keyboardActions = KeyboardActions { focusManager.clearFocus() },
        isError = state.passwordError != null
    )

    AuthTextField(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 64.dp),
        tfValue = state.email,
        textFieldConfig = emailConfig,
        errorMessage = state.emailError
    ) { viewModel.onEvent(LoginEvent.EmailChange(it)) }

    Spacer(modifier = Modifier.height(8.dp))

    AuthTextField(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 64.dp),
        tfValue = state.password,
        textFieldConfig = passwordConfig,
        errorMessage = state.passwordError
    ) { viewModel.onEvent(LoginEvent.PasswordChange(it)) }
}