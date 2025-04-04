package com.example.fitnik.authentication.presentation.setUpAccInfo.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.components.getTextFieldColors
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.ui.theme.black
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun InputsGroup(
    modifier: Modifier = Modifier
) {
    val genderOptions = listOf("Male", "Female", "Other")
    val activityOptions = listOf("Male", "Female", "Other")
    var genderOptionSelection by remember { mutableStateOf("") }
    var datePickerIsOpen by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(LocalDate.now()) }

    val buttonTextStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)

    DynamicSelectTextField(
        options = genderOptions,
        label = "Choose Gender",
        selectedValue = genderOptionSelection
    ) { value ->
        genderOptionSelection = value
    }
    Spacer(modifier = Modifier.height(12.dp))
    ActivityInfoRow(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(12.dp))
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = date.format(DateTimeFormatter.ISO_DATE),
        label = { Text("Birth Date") },
        onValueChange = {},
        colors = getTextFieldColors().copy(
            unfocusedTextColor = black
        ),
        trailingIcon = { IconButton(
            onClick = { datePickerIsOpen = true }
        ) { Icon(painter = painterResource(R.drawable.calendar), "Calendar Icon") } }
    )
    Spacer(modifier = Modifier.height(12.dp))
    MeasuresTextFieldRow(modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(12.dp))
    MeasuresTextFieldRow(modifier = Modifier.fillMaxWidth(), isWeightTextField = false)
    Spacer(modifier = Modifier.height(32.dp))
    FitnikDefButton(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 62.dp),
        onAction = {  }
    ) { Text("Confirm", style = buttonTextStyle) }
    if (datePickerIsOpen) {
        CustomDatePicker(
            onAccept = { datePickeVal ->
                datePickerIsOpen = false
                if (datePickeVal != null) {
                    date = Instant
                        .ofEpochMilli(datePickeVal)
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()
                }
            },
            onCancel = { datePickerIsOpen = false },
        )
    }
}