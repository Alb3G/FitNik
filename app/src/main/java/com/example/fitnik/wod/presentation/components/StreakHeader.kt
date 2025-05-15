package com.example.fitnik.wod.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.ui.theme.black
import com.example.fitnik.ui.theme.darkGray
import com.example.fitnik.ui.theme.error
import com.example.fitnik.ui.theme.white

@Composable
fun StreakHeader(streak: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = white
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Dailt Streak",
                    style = MaterialTheme.typography.titleLarge,
                    color = black
                )
                Text(
                    text = "¡Keep it up!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = darkGray
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(R.drawable.streak_icon),
                    contentDescription = "Streak",
                    tint = error
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "$streak days",
                    style = MaterialTheme.typography.headlineLarge,
                    color = black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}