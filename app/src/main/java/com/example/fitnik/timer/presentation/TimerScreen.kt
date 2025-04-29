package com.example.fitnik.timer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.timerButton

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 136.dp),
            text = state.time,
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
           Button(
               modifier = Modifier
                   .defaultMinSize(minHeight = 24.dp)
                   .weight(1f),
               shape = RoundedCornerShape(8.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = primary
               ),
               onClick = {}
           ) {
               Text(state.resetLapButtonText, style = MaterialTheme.typography.bodyLarge)
           }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .defaultMinSize(minHeight = 24.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = timerButton
                ),
                onClick = {}
            ) {
                Text(state.startButtonText, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}