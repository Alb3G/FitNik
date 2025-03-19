package com.example.fitnik.core.model

import androidx.compose.foundation.text.KeyboardActions

data class TextFieldConfig(
    val label: String,
    val leadingIcon: Int,
    val contentDescription: String = "",
    val keyboardActions: KeyboardActions,
    val isEmail: Boolean = false,
    val isPassword: Boolean = false,
)