package com.example.fitnik.wod.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.wod.presentation.components.StreakCalendar
import com.example.fitnik.wod.presentation.components.StreakHeader
import com.example.fitnik.wod.presentation.components.WodCard
import com.example.fitnik.wod.presentation.components.WorkoutTimerSection

@Composable
fun WodChallengeScreen(
    modifier: Modifier = Modifier,
    state: WodState,
    onCompleteWod: () -> Unit,
    onStartWorkout: () -> Unit,
    onCancelWorkout: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(smokeWhite)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with streak counter
        StreakHeader(streak = state.streak)

        Spacer(modifier = Modifier.height(24.dp))

        // WOD Card
        WodCard(
            wod = state.currentWod,
            isCompleted = state.isCompleted,
            onCompleteClick = { if (!state.isCompleted) onCompleteWod() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Workout Timer Section
        WorkoutTimerSection(
            isWorkoutActive = state.isWorkoutActive,
            remainingSeconds = state.remainingSeconds,
            totalSeconds = state.currentWod.durationMinutes * 60,
            onStartWorkout = { if (!state.isWorkoutActive && !state.isCompleted) onStartWorkout() },
            onCancelWorkout = { if (state.isWorkoutActive) onCancelWorkout() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Streak Calendar
        StreakCalendar(history = state.history)
    }
}

// Helper function to format seconds into MM:SS
fun formatTime(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}