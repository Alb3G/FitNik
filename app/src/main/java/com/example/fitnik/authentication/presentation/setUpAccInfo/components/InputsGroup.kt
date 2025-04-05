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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.components.getTextFieldColors
import com.example.fitnik.authentication.presentation.setUpAccInfo.AccounEvent
import com.example.fitnik.authentication.presentation.setUpAccInfo.SetUpAccState
import com.example.fitnik.authentication.presentation.setUpAccInfo.SetUpAccViewModel
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.ui.theme.black
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun InputsGroup(
    modifier: Modifier = Modifier,
    viewModel: SetUpAccViewModel,
    state: SetUpAccState
) {
    val context = LocalContext.current
    val genderOptions = listOf("Male", "Female", "Other")
    var datePickerIsOpen by remember { mutableStateOf(false) }


    val buttonTextStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)

    // GENDER SELECT
    DynamicSelectTextField(
        options = genderOptions,
        label = "Choose Gender",
        selectedValue = state.gender
    ) { value ->
        viewModel.onEvent(AccounEvent.CollectGender(value))
    }

    Spacer(modifier = Modifier.height(12.dp))

    // ACTIVITY AND OBJECTIVE ROW
    ActivityInfoRow(
        modifier = Modifier.fillMaxWidth(),
        onActivityChange =  { activity ->
            viewModel.onEvent(AccounEvent.CollectActivity(activity))
        },
        onObjectiveChange = { objective ->
            viewModel.onEvent(AccounEvent.CollectObjective(objective))
        },
        value = state.activity
    )

    Spacer(modifier = Modifier.height(12.dp))

    // DATEPICKER
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = state.birthDate.format(DateTimeFormatter.ISO_DATE),
        label = { Text("Birth Date") },
        onValueChange = { viewModel.onEvent(AccounEvent.CollectBirthDate(state.birthDate)) },
        colors = getTextFieldColors().copy(
            unfocusedTextColor = black
        ),
        trailingIcon = { IconButton(
            onClick = { datePickerIsOpen = true }
        ) { Icon(painter = painterResource(R.drawable.calendar), "Calendar Icon") } }
    )

    Spacer(modifier = Modifier.height(12.dp))

    // WEIGHT TEXTFIELD
    MeasuresTextFieldRow(
        modifier = Modifier.fillMaxWidth(),
        isPrimaryUnit = state.isWeightInKg,
        value = state.weight.toString(),
        onValueChange = {
            viewModel.onEvent(AccounEvent.CollectWeight(it))
        },
        onUnitToggle = { viewModel.onEvent(AccounEvent.ToggleWeightUnit) },
    )

    Spacer(modifier = Modifier.height(12.dp))

    // HEIGHT TEXTFIELD
    MeasuresTextFieldRow(
        modifier = Modifier.fillMaxWidth(),
        isWeightTextField = false,
        isPrimaryUnit = state.isHeightInCm,
        value = state.height.toString(),
        onValueChange = {
            viewModel.onEvent(AccounEvent.CollectHeight(it))
        },
        onUnitToggle = { viewModel.onEvent(AccounEvent.ToggleHeightUnit) },
    )

    Spacer(modifier = Modifier.height(32.dp))

    FitnikDefButton(
        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 62.dp),
        onAction = { viewModel.onEvent(AccounEvent.Confirm(context)) }
    ) { Text("Confirm", style = buttonTextStyle) }
    if (datePickerIsOpen) {
        CustomDatePicker(
            onAccept = { datePickeVal ->
                datePickerIsOpen = false
                if (datePickeVal != null) {
                    state.birthDate = Instant
                        .ofEpochMilli(datePickeVal)
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()
                }
            },
            onCancel = { datePickerIsOpen = false },
        )
    }
}