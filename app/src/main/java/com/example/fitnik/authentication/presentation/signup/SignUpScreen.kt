package com.example.fitnik.authentication.presentation.signup

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.authentication.presentation.signup.components.InputGroup
import com.example.fitnik.authentication.presentation.signup.components.PDFTermsScreen
import com.example.fitnik.authentication.presentation.signup.components.PasswordRequirements
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.core.presentation.LoginDivider
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.white

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onLoginClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val buttonTextStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
    var showTerms by remember { mutableStateOf(false) }

    if (showTerms) {
        PDFTermsScreen(
            fileName = "Terms.pdf",
            onDisplay = { showTerms = !showTerms }
        )
    } else {
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

            Text("Create an Account", fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(24.dp))

            InputGroup(viewModel)

            Spacer(modifier = Modifier.height(8.dp))

            PasswordRequirements(state.password, viewModel)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.privacyConsent,
                    onCheckedChange = { viewModel.onEvent(SignUpEvent.PrivacyConsentChange(it)) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = primary,
                        uncheckedColor = lightGray,
                        checkmarkColor = white
                    ),
                )
                TextButton(onClick = { showTerms = !showTerms }) {
                    Text(
                        text = "By continuing you accept our Privacy Policy and Terms of Use",
                        style = MaterialTheme.typography.bodySmall.copy(
                            textDecoration = TextDecoration.Underline,
                            color = black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            FitnikDefButton(
                modifier = Modifier
                .defaultMinSize(minHeight = 84.dp)
                .fillMaxWidth()
                .padding(bottom = 24.dp),
                enabled = viewModel.signUpAllowed,
                onAction = { /* Implementacion del proceso de registro */ }
            ) { Text("Sign Up", style = buttonTextStyle) }

            LoginDivider()

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already have an account?", style = MaterialTheme.typography.bodyMedium)

                TextButton(
                    onClick = onLoginClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = secondary
                    )
                ) {
                    Text("Login", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }

}