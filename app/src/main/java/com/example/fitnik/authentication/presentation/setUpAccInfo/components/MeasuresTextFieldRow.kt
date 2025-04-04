package com.example.fitnik.authentication.presentation.setUpAccInfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.components.getTextFieldColors
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.secondary2
import com.example.fitnik.ui.theme.white

@Composable
fun MeasuresTextFieldRow(
    modifier: Modifier = Modifier,
    isWeightTextField: Boolean = true
) {
    var buttonText by remember { mutableStateOf(false) }

    Row(
       modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = "",
            label = {
                if (isWeightTextField) Text("Your Weight") else Text("Your Height")
            },
            onValueChange = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                if (isWeightTextField)
                    Icon(painter = painterResource(R.drawable.weight_scale), "Weight Scale Icon")
                else
                    Icon(painter = painterResource(R.drawable.swap), "Height Icon")
            },
            colors = getTextFieldColors().copy(
                unfocusedTextColor = black
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            modifier = Modifier
                .defaultMinSize(minHeight = 56.dp)
                .padding(top = 4.dp)
                .background(Brush.horizontalGradient(
                    colors = listOf(secondary2, secondary)
                ),
                shape = RoundedCornerShape(percent = 20)
            ),
            onClick = { buttonText = !buttonText },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = white,
            ),
            contentPadding = PaddingValues(10.dp)
        ) {
            if (isWeightTextField)
                Text(if(!buttonText) "KG" else "LB")
            else
                Text(if(!buttonText) "CM" else "FT")
        }
    }
}