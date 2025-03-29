package com.example.fitnik.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.core.presentation.AuthIconButton
import com.example.fitnik.ui.theme.lightGray

@Composable
fun LoginOptionsComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthIconButton(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(width = 1.dp, color = lightGray, shape = RoundedCornerShape(12.dp))
                .background(Color.Transparent),
            iconResId = R.drawable.icons8_google,
            contentDescription = "Sign Up with google"
        ) { /* Login with Google */ }
        Spacer(modifier = Modifier.width(24.dp))
        AuthIconButton(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(width = 1.dp, color = lightGray, shape = RoundedCornerShape(12.dp))
                .background(Color.Transparent),
            iconResId = R.drawable.icons8_github,
            contentDescription = "Sign Up with Github"
        ) { /* Login with github */ }
    }
}