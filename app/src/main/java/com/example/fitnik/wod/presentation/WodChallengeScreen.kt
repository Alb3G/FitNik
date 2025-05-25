package com.example.fitnik.wod.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.wod.presentation.components.StreakCalendar
import com.example.fitnik.wod.presentation.components.StreakHeader
import com.example.fitnik.wod.presentation.components.WodCard

@Composable
fun WodChallengeScreen(
    modifier: Modifier = Modifier,
    state: WodState,
    onCompleteWod: () -> Unit,
    onNextPeriod: () -> Unit,
    onPreviousPeriod: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(smokeWhite)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header with streak counter
        StreakHeader(streak = state.streak)

        Spacer(modifier = Modifier.height(24.dp))

        // WOD Card
        state.currentWod?.let {
            WodCard(
                wod = it,
                isCompleted = state.isCompleted,
                onCompleteClick = { if (!state.isCompleted) onCompleteWod() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Streak Calendar
        StreakCalendar(
            history = state.history,
            onPreviousPeriod = onPreviousPeriod,
            onNextPeriod = onNextPeriod
        )
    }
}