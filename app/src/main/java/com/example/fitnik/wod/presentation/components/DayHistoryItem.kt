package com.example.fitnik.wod.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fitnik.core.domain.model.WodHistory
import com.example.fitnik.ui.theme.darkGray
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.ui.theme.white
import java.time.format.DateTimeFormatter

@Composable
fun DayHistoryItem(dayHistory: WodHistory) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM")
    val dayFormatter = DateTimeFormatter.ofPattern("EEE")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = dayHistory.date.format(dayFormatter),
            style = MaterialTheme.typography.bodySmall,
            color = darkGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(if (dayHistory.completed) primary else smokeWhite)
                .border(
                    width = 1.dp,
                    color = if (dayHistory.completed) primary else lightGray,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (dayHistory.completed) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Completed",
                    tint = white,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = dayHistory.date.format(DateTimeFormatter.ofPattern("d")),
                    style = MaterialTheme.typography.bodyMedium,
                    color = darkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = dayHistory.date.format(dateFormatter),
            style = MaterialTheme.typography.labelSmall,
            color = darkGray
        )
    }
}