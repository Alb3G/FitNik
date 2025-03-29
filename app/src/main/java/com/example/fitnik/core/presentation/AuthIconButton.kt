package com.example.fitnik.core.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AuthIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }

}