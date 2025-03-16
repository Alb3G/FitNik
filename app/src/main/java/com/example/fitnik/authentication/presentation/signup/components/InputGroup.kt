package com.example.fitnik.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.core.presentation.AuthTextField

@Composable
fun InputGroup() {
    AuthTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        tfValue = "Alberto",
        label = "First Name",
        leadingIcon = R.drawable.profile,
        contentDescription = "Profile icon",
    )
    Spacer(modifier = Modifier.height(24.dp))
    AuthTextField(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        tfValue = "",
        label = "Last Name",
        leadingIcon = R.drawable.profile,
        contentDescription = "Profile icon",
    )
    Spacer(modifier = Modifier.height(24.dp))
    AuthTextField(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        tfValue = "EMail",
        label = "Email",
        leadingIcon = R.drawable.message,
        contentDescription = "Profile icon",
        isEmail = true
    )
    Spacer(modifier = Modifier.height(24.dp))
    AuthTextField(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        tfValue = "Password1.",
        label = "Password",
        leadingIcon = R.drawable.lock,
        contentDescription = "Profile icon",
        isPassword = true
    )
}