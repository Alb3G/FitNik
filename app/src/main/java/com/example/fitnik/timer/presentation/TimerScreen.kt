package com.example.fitnik.timer.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.fitnik.core.presentation.RequestNotificationPermission
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.error
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite
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
        modifier = Modifier.background(smokeWhite).fillMaxSize().padding(24.dp),
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

        // Button Row Lap - Start
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
                onClick = { if (!state.isRunning) viewModel.resetTimer() else viewModel.onLapClick() }
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

        // Laps lazy column
        LazyColumn(
            modifier = Modifier.padding(top = 48.dp)
        ) {
            items(
                items = state.times.reversed(),
                key = { item -> state.times.indexOf(item) }
            ) { item ->
                val lapItemColor = if (viewModel.lowestTime(item)) {
                    timerButton
                } else if (viewModel.biggestTime(item)) {
                    error
                } else {
                    black
                }
                HorizontalDivider()
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Lap ${state.times.indexOf(item) + 1}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily.Monospace
                        ),
                        color = lapItemColor
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = formatTime(item),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily.Monospace
                        ),
                        color = lapItemColor
                    )
                }
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
