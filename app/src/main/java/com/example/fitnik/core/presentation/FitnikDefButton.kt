package com.example.fitnik.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.primary2
import com.example.fitnik.ui.theme.white

@Composable
fun FitnikDefButton(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    enabled: Boolean,
    onAction: () -> Unit,
) {
    Button(
        onClick = onAction,
        modifier = modifier.background(
            Brush.horizontalGradient(
                colors = listOf(primary2, primary)
            ),
            shape = RoundedCornerShape(percent = 50)
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Transparente para mostrar el gradiente
            contentColor = white,
            disabledContainerColor = primary.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(), // Evita padding extra que pueda tapar el gradiente
        enabled = enabled
    ) {
        Text(text = text, style = textStyle)
    }
}
