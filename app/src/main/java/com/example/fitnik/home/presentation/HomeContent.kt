package com.example.fitnik.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.home.presentation.components.NoWorkoutsComponent
import com.example.fitnik.home.presentation.components.WorkoutCard
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.ui.theme.white

@Composable
fun HomContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onCreateWorkoutClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(smokeWhite)
            .padding(horizontal = 24.dp)
    ) {
        val state by viewModel.state.collectAsState()

        if (state.workouts.isEmpty()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NoWorkoutsComponent()
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Your Workouts",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de entrenamientos
                state.workouts.forEach { workout ->
                    WorkoutCard(
                        workout = workout,
                        onClick = { /* Navegar al detalle del entreno */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }

                // Espacio extra al final para que el FAB no tape el último elemento
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        // Botón flotante para crear un nuevo entreno
        FloatingActionButton(
            onClick = onCreateWorkoutClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Workout",
                tint = white
            )
        }
    }
}