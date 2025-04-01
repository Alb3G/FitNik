package com.example.fitnik.authentication.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.login.components.InputGroup
import com.example.fitnik.authentication.presentation.login.components.LoginOptionsComponent
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.core.presentation.LoginDivider
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.white

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onRegisterClick: () -> Unit,
    onLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val buttonTextStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
    val context = LocalContext.current

    LaunchedEffect(key1 = state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLogin()
        }
    }

    Column(
            modifier = Modifier
                .background(white)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hey there,", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(6.dp))

        Text("Welcome Back", fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        InputGroup(viewModel)

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { /* Logica de restablecer contraseña */ }
            ) { Text("Forgot your password?") }
        }

        Spacer(modifier = Modifier.weight(0.5f))

        FitnikDefButton(
            modifier = Modifier
                .defaultMinSize(minHeight = 84.dp)
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            enabled = !state.isLoading,
            onAction = { viewModel.login() }
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = white)
            } else {
                Icon(
                    painter = painterResource(R.drawable.login),
                    contentDescription = "Login Button Icon",
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(text = "Login", style = buttonTextStyle)
            }
        }

        LoginDivider()

        Spacer(modifier = Modifier.height(24.dp))

        LoginOptionsComponent(
            onGoogleLogin = { viewModel.loginWithGoogle(context) },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account yet?", style = MaterialTheme.typography.bodyMedium)
            TextButton(
                onClick = onRegisterClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = secondary
                )
            ) {
                Text("Sign Up", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}