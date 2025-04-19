package com.example.fitnik.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnik.R

@Composable
fun NoWorkoutsComponent() {
    Image(
        painter = painterResource(R.drawable.appicon),
        "App Icon",
        modifier = Modifier.alpha(0.3f)
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text("No Workouts Available", style = MaterialTheme.typography.headlineSmall)
}