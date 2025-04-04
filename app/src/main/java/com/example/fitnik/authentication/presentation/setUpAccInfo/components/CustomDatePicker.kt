package com.example.fitnik.authentication.presentation.setUpAccInfo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnik.core.presentation.FitnikDefButton
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    onAccept: (Long?) -> Unit,
    onCancel: () -> Unit
) {
    val today = Instant.now().toEpochMilli()
    val state = rememberDatePickerState(
        initialSelectedDateMillis = today,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= today
            }
        }
    )

    DatePickerDialog(
        onDismissRequest = {},
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                FitnikDefButton(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 32.dp, minWidth = 100.dp),
                    onAction = onCancel
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(12.dp))
                FitnikDefButton(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 32.dp, minWidth = 100.dp),
                    onAction = { onAccept(state.selectedDateMillis) }
                ) {
                    Text("Accept")
                }
            }
        },
        dismissButton = {},
    ) {
        DatePicker(state = state)
    }
}