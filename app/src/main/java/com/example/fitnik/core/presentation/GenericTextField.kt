package com.example.fitnik.core.presentation

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.error
import com.example.fitnik.ui.theme.midGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.primary2

@Composable
fun GenericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
    minHeight: Dp = 56.dp,
    shape: Shape = RoundedCornerShape(12.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    colors: TextFieldColors = defaultGenericTextFieldColors()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minHeight),
        textStyle = androidx.compose.ui.text.TextStyle(color = black),
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = shape,
        colors = colors
    )
}


@Composable
fun defaultGenericTextFieldColors(): TextFieldColors =
    OutlinedTextFieldDefaults.colors(
        // Fondo
        unfocusedContainerColor = primary2.copy(alpha = 0.2f),
        focusedContainerColor = primary2.copy(alpha = 0.2f),
        // Bordes
        focusedBorderColor = primary,
        unfocusedBorderColor = primary,
        errorBorderColor = error,
        // Cursor y texto
        cursorColor = primary,
        unfocusedTextColor = black,
        focusedTextColor = black,
        // Placeholder y label
        unfocusedPlaceholderColor = midGray,
        focusedPlaceholderColor = midGray,
        unfocusedLabelColor = midGray,
        focusedLabelColor = primary,
        errorLabelColor = error,
        // Iconos
        unfocusedLeadingIconColor = midGray,
        focusedLeadingIconColor = primary,
        errorLeadingIconColor = error,
        unfocusedTrailingIconColor = midGray,
        focusedTrailingIconColor = primary,
        errorTrailingIconColor = error
    )
