package com.example.fitnik.wod.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.core.domain.model.Wod
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.darkGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.timerButton
import com.example.fitnik.ui.theme.white

@Composable
fun WodCard(
    wod: Wod,
    isCompleted: Boolean,
    onCompleteClick: () -> Unit
) {
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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "WOD de hoy",
                    style = MaterialTheme.typography.titleLarge,
                    color = primary
                )

                if (isCompleted) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = "Completed",
                            tint = timerButton
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Completado",
                            style = MaterialTheme.typography.bodyMedium,
                            color = timerButton
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // WOD Name
            Text(
                text = wod.name,
                style = MaterialTheme.typography.displaySmall,
                color = black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // WOD Description
            Text(
                text = wod.description,
                style = MaterialTheme.typography.bodyLarge,
                color = darkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // WOD Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WodDetailItem(
                    icon = R.drawable.laps_icon,
                    value = "${wod.rounds}",
                    label = "Rondas"
                )

                WodDetailItem(
                    icon = R.drawable.timer_icon,
                    value = "${wod.durationMinutes}",
                    label = "Minutos"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Complete Button
            Button(
                onClick = onCompleteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isCompleted) timerButton else primary,
                    contentColor = white
                ),
                shape = RoundedCornerShape(16.dp),
                enabled = !isCompleted
            ) {
                Text(
                    text = if (isCompleted) "¡WOD Completado!" else "Completar WOD",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}