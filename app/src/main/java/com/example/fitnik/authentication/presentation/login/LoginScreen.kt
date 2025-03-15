package com.example.fitnik.authentication.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.login.components.LoginTextField
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.white

@Composable
fun LoginScreen() {
    val isChecked = false
    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hey there,", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(6.dp))
        Text("Create an Account", fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        InputGroup()
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {  },
                colors = CheckboxDefaults.colors(
                    checkedColor = primary,
                    uncheckedColor = lightGray,
                    checkmarkColor = white
                ),
            )
            Text(
                text = "By continuing you accept our Privacy Policy and Terms of Use",
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(110.dp))
        FitnikDefButton(
            text = "Sign Up",
            modifier = Modifier
            .defaultMinSize(minHeight = 64.dp)
            .fillMaxWidth(),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp
            )
        ) {  }
    }
}

@Composable
fun InputGroup() {
    LoginTextField(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        tfValue = "",
        label = "First Name",
        leadingIcon = R.drawable.profile,
        contentDescription = "Profile icon",
    )
    Spacer(modifier = Modifier.height(24.dp))
    LoginTextField(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        tfValue = "",
        label = "Last Name",
        leadingIcon = R.drawable.profile,
        contentDescription = "Profile icon",
    )
    Spacer(modifier = Modifier.height(24.dp))
    LoginTextField(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        tfValue = "",
        label = "Email",
        leadingIcon = R.drawable.message,
        contentDescription = "Profile icon",
        isEmail = true
    )
    Spacer(modifier = Modifier.height(24.dp))
    LoginTextField(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        tfValue = "",
        label = "Password",
        leadingIcon = R.drawable.lock,
        contentDescription = "Profile icon",
        isPassword = true
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hey there,", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Create an Account", fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        LoginTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            tfValue = "",
            label = "First Name",
            leadingIcon = R.drawable.profile,
            contentDescription = "Profile icon",
        )
    }
}