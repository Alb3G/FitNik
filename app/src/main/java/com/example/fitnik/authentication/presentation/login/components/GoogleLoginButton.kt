package com.example.fitnik.authentication.presentation.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GoogleLoginButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Row {
            Text("Login with google")
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(iconResId),
                contentDescription = contentDescription,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}