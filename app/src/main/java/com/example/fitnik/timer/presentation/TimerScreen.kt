package com.example.fitnik.timer.presentation

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.authentication.presentation.signup.components.RequestNotificationPermission
import com.example.fitnik.ui.theme.error
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.timerButton

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val startButtonColor = when (state.isRunning) {
        true -> error
        false -> timerButton
    }

    RequestNotificationPermission { }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 136.dp),
            text = formatTime(state.time),
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 64.sp,
                fontFamily = FontFamily.Monospace
            )
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
                onClick = { if (!state.isRunning) viewModel.resetTimer() }
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
                    containerColor = startButtonColor
                ),
                onClick = { viewModel.toggleStartAndStop() }
            ) {
                Text(state.startButtonText, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(time: Long): String {
    val centiseconds = time % 100
    val seconds = (time / 100) % 60
    val minutes = (time / 6000) % 60

    return String.format("%02d:%02d:%02d", minutes, seconds, centiseconds)
}
