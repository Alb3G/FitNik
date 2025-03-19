package com.example.fitnik.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.core.model.TextFieldConfig
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.midGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.ui.theme.white

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    tfValue: String,
    textFieldConfig: TextFieldConfig,
    onValueChange: (String) -> Unit,
) {

    var passVisible by remember { mutableStateOf(false) }

    val keyboardOptions = if (textFieldConfig.isEmail) {
        KeyboardOptions(keyboardType = KeyboardType.Email)
    } else if (textFieldConfig.isPassword) {
        KeyboardOptions(keyboardType = KeyboardType.Password)
    } else {
        KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    val trailingIcon: @Composable (() -> Unit)? = if (textFieldConfig.isPassword) {
        {
            IconButton(onClick = { passVisible = !passVisible }) {
                Icon(
                    painter = if (passVisible) painterResource(R.drawable.hide) else painterResource(R.drawable.show),
                    contentDescription = "Show password icon"
                )
            }
        }
    } else {
        null
    }

    OutlinedTextField(
        modifier = modifier.semantics { this.contentDescription = contentDescription },
        value = tfValue,
        onValueChange = { onValueChange(it) },
        label = { Text(textFieldConfig.label) },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = smokeWhite,
            unfocusedContainerColor = smokeWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = lightGray,
            focusedLeadingIconColor = primary,
            focusedLabelColor = primary
        ),
        leadingIcon = {
            Icon(painter = painterResource(textFieldConfig.leadingIcon), textFieldConfig.contentDescription)
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (textFieldConfig.isPassword && !passVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = trailingIcon,
        keyboardActions = textFieldConfig.keyboardActions
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginTextFieldPreview() {
    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            value = "",
            onValueChange = { it },
            label = { Text("First Name") },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = smokeWhite,
                focusedContainerColor = midGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                unfocusedTextColor = midGray,
                focusedTextColor = midGray
            ),
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.profile), "Profile icon")
            }
        )
    }
}