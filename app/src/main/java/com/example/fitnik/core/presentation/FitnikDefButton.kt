package com.example.fitnik.core.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.white

@Composable
fun FitnikDefButton(
    text: String,
    modifier: Modifier = Modifier,
    onAction: () -> Unit
) {
    Button(
        onClick = onAction,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = primary,
            contentColor = white
        )
    ) { Text(text = text, style = MaterialTheme.typography.titleMedium) }
}