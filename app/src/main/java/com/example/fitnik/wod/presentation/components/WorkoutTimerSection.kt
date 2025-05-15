package com.example.fitnik.wod.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.darkGray
import com.example.fitnik.ui.theme.error
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.timerButton
import com.example.fitnik.ui.theme.white
import com.example.fitnik.wod.presentation.formatTime

@Composable
fun WorkoutTimerSection(
    isWorkoutActive: Boolean,
    remainingSeconds: Int,
    totalSeconds: Int,
    onStartWorkout: () -> Unit,
    onCancelWorkout: () -> Unit
) {
    val progress = if (totalSeconds > 0) {
        1f - (remainingSeconds.toFloat() / totalSeconds.toFloat())
    } else {
        0f
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "progress"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = white
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temporizador",
                style = MaterialTheme.typography.titleLarge,
                color = primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.size(200.dp),
                    color = secondary,
                    trackColor = lightGray,
                    strokeCap = StrokeCap.Round,
                    strokeWidth = 12.dp
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = formatTime(remainingSeconds),
                        style = MaterialTheme.typography.displayLarge,
                        color = black
                    )

                    Text(
                        text = if (isWorkoutActive) "En progreso" else "Listo para comenzar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = darkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (isWorkoutActive) {
                    Button(
                        onClick = onCancelWorkout,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = error,
                            contentColor = white
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                } else {
                    Button(
                        onClick = onStartWorkout,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = timerButton,
                            contentColor = black
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Iniciar WOD",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}