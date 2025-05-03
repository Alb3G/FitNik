package com.example.fitnik.createRoutine.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.primary2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineScreen(
    viewModel: CreateRoutineViewModel = hiltViewModel(),
    onFinish: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 80.dp) // Dejamos espacio para el botón
            ) {
                // Routine Name
                OutlinedTextField(
                    value = state.routineName,
                    onValueChange = { viewModel.onEvent(CreateRoutineEvent.RoutineNameChange(it)) },
                    label = { Text("Routine Name") },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Create Workout
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = state.workoutName,
                        onValueChange = { viewModel.onEvent(CreateRoutineEvent.WorkoutNameChange(it)) },
                        label = { Text("New Workout Name") },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f),
                        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primary
                        ),
                        onClick = {
                            viewModel.onEvent(CreateRoutineEvent.AddWorkout)
                        }
                    ) {
                        Text("Crear Workout")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Workout List
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = state.workouts,
                        key = { workout -> workout.id }
                    ) { workout ->
                        val workoutIndex = state.workouts.indexOf(workout)
                        WorkoutItem(
                            workout = workout,
                            viewModel = viewModel,
                            focusManager = focusManager,
                            workoutIndex = workoutIndex
                        )
                    }
                }
            }

            // Save Routine Button (fijo abajo)
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    viewModel.onEvent(CreateRoutineEvent.SaveRoutine)
                    onFinish()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primary
                )
            ) {
                Text(
                    text = "Save Routine",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun WorkoutItem(
    workout: Workout,
    viewModel: CreateRoutineViewModel,
    focusManager: FocusManager,
    workoutIndex: Int
) {
    // Estado local para los campos de texto
    var exerciseName by remember { mutableStateOf("") }
    var exerciseSets by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(workout.isExpanded) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                isExpanded = !isExpanded
                // Al hacer clic, indicarle al ViewModel qué workout está seleccionado
                viewModel.setCurrentWorkoutIndex(workoutIndex)
            }
            .animateContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = primary2.copy(alpha = 0.2f)),
        border = BorderStroke(width = 2.dp, color = primary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = workout.name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
            )

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))

                // Lista de ejercicios dentro de la caja del workout
                Column {
                    workout.exercises.forEach { exercise ->
                        Text("- ${exercise.name} (${exercise.sets.size} sets)", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = exerciseName,
                        onValueChange = {
                            exerciseName = it
                        },
                        label = { Text("Exercise") },
                        singleLine = true,
                        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        modifier = Modifier.width(80.dp),
                        value = exerciseSets,
                        onValueChange = {
                            exerciseSets = it
                        },
                        label = { Text("Sets") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions { focusManager.clearFocus() }
                    )
                }

                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp),
                    onClick = {
                        if (exerciseName.isNotBlank() && exerciseSets.isNotBlank()) {
                            // Seleccionar este workout y establecer los valores en el ViewModel
                            viewModel.setCurrentWorkoutIndex(workoutIndex)
                            // Actualizar los valores en el ViewModel justo antes de agregar
                            viewModel.updateExerciseFields(exerciseName, exerciseSets)
                            // Disparar el evento para agregar el ejercicio
                            viewModel.onEvent(CreateRoutineEvent.AddExercise)
                            // Limpiar los campos locales
                            exerciseName = ""
                            exerciseSets = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primary
                    )
                ) {
                    Text("Añadir Ejercicio")
                }
            }
        }
    }
}