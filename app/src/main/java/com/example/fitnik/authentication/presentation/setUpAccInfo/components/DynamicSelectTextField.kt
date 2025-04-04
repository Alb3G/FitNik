package com.example.fitnik.authentication.presentation.setUpAccInfo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.fitnik.R
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    modifier: Modifier = Modifier,
    options: List<String>,
    label: String,
    selectedValue: String,
    displayIn2Sections: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            leadingIcon = { Icon(painter = painterResource(R.drawable.profile), "Gender Icon") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = smokeWhite,
                unfocusedContainerColor = smokeWhite,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = lightGray,
                focusedLeadingIconColor = primary,
                focusedLabelColor = primary,
            ),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .then(if (!displayIn2Sections) Modifier.fillMaxWidth() else Modifier)
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option: String ->
                DropdownMenuItem(
                    text = {
                        Text(text = option)
                    },
                    onClick = {
                        expanded = false
                        val shortValue = option.split(":")[0].trim()
                        onValueChange(shortValue)
                    }

                )
            }
        }
    }
}