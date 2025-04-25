package com.example.fitnik.routineDetail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnik.ui.theme.smokeWhite

@Composable
fun RoutineDetailScreen(routineId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(smokeWhite)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("RoutineDetailScreen")
        Spacer(modifier = Modifier.height(8.dp))
        Text(routineId)
    }
}