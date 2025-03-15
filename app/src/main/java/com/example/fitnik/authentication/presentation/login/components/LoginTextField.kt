package com.example.fitnik.authentication.presentation.login.components

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.midGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.ui.theme.white

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    tfValue: String,
    label: String,
    leadingIcon: Int,
    contentDescription: String = "",
    isEmail: Boolean = false,
    isPassword: Boolean = false,
) {

    val keyboardOptions = if (isEmail) KeyboardOptions(
        keyboardType = KeyboardType.Email
    ) else KeyboardOptions(keyboardType = KeyboardType.Password)

    val trailingIcon: @Composable (() -> Unit)? = if (isPassword) {
        {
            Icon(
                painter = painterResource(R.drawable.hide),
                contentDescription = "Show password icon"
            )
        }
    } else {
        null
    }

    OutlinedTextField(
        modifier = modifier,
        value = tfValue,
        onValueChange = { it },
        label = { Text(label) },
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
            Icon(painter = painterResource(leadingIcon), contentDescription)
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon
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