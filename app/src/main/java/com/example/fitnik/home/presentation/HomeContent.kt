package com.example.fitnik.home.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.home.presentation.components.NoWorkoutsComponent
import com.example.fitnik.home.presentation.components.RoutineCard
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite
import com.example.fitnik.ui.theme.white

@Composable
fun HomContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onCreateRoutineClick: () -> Unit,
    onRoutineClick: (String) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(smokeWhite)
            .padding(horizontal = 24.dp)
    ) {
        val state by viewModel.state.collectAsState()

        // Contenido principal - siempre presente
        AnimatedVisibility(
            visible = !state.isLoading && state.routines.isNotEmpty(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item {
                    Text(
                        "Your Routines",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                items(state.routines) { routine ->
                    RoutineCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        routine = routine,
                        onClick = {
                            onRoutineClick(routine.id)
                            Log.d("RoutineCard", "Routine clicked with ID: ${routine.id}")
                        },
                        onDeleteRoutine = { viewModel.deleteRoutine(routine.id, context) }
                    )
                }

                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }

        // Mensaje de no rutinas - animado
        AnimatedVisibility(
            visible = !state.isLoading && state.routines.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            NoWorkoutsComponent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                text = "No Routines Available"
            )
        }

        // Indicador de carga - animado
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        // FAB - siempre presente
        FloatingActionButton(
            onClick = onCreateRoutineClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Routine",
                tint = white
            )
        }
    }
}