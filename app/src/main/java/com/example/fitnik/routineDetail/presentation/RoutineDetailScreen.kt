package com.example.fitnik.routineDetail.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.smokeWhite

@Composable
fun RoutineDetailScreen(
    routineId: String,
    viewModel: RoutineDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(routineId) {
        viewModel.loadWorkouts(routineId)
    }

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(smokeWhite)
            .padding(horizontal = 16.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.error != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.error ?: "Unknown error",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            state.workouts.isEmpty() -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No workouts found",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Try creating a new workout",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Routine Details",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }

                    items(
                        items = state.workouts,
                        key = { it.id }
                    ) { workout ->
                        ExpandableWorkoutBox(
                            workout = workout,
                            isExpanded = workout.isExpanded,
                            onExpandToggle = {
                                viewModel.toggleWorkoutIsExpanded(workout.id)
                            },
                            onSetValueChange = { exerciseId, setIndex, weight, reps ->
                                viewModel.updateWorkoutSet(workout.id, exerciseId,setIndex, weight ,reps)
                            },
                            onSaveExerciseProgress = { exerciseId, sets ->
                                viewModel.updateWorkoutSets(exerciseId, sets, context)
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableWorkoutBox(
    workout: Workout,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    onSetValueChange: (exerciseId: String, setIndex: Int, weight: Double?, reps: Int?) -> Unit,
    onSaveExerciseProgress: (exerciseId: String, workoutSets: List<WorkoutSet>) -> Unit
) {
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "rotation"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer()
            .clip(RoundedCornerShape(8.dp))
            .background(primary.copy(alpha = 0.3f))
            .border(BorderStroke(width = 2.dp, color = primary.copy(alpha = 0.5f)))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onExpandToggle() }
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = workout.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    modifier = Modifier.rotate(rotationState)
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    workout.exercises.forEachIndexed { index, exercise ->
                        if (index > 0) {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                        ExerciseItem(
                            exercise = exercise,
                            onSetValueChange = { setIndex, weight, reps ->
                                onSetValueChange(exercise.id, setIndex, weight, reps)
                            }
                        )
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = { onSaveExerciseProgress(exercise.id, exercise.sets) },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primary
                            )
                        ) {
                            Text(
                                text = "Save Progress",
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onSetValueChange: (setIndex: Int, weight: Double?, reps: Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Text(
            text = exercise.name,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        exercise.sets.forEachIndexed { index, set ->
            EditableSetItem(
                setNumber = index + 1,
                set = set,
                onValueChange = { weight, reps ->
                    onSetValueChange(index, weight, reps)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EditableSetItem(
    setNumber: Int,
    set: WorkoutSet,
    onValueChange: (weight: Double?, reps: Int?) -> Unit
) {
    // 1) Creamos estados locales que sólo se inicializan la primera vez
    var weightText by rememberSaveable { mutableStateOf(set.weight?.toString() ?: "") }
    var repsText   by rememberSaveable { mutableStateOf(set.reps?.toString() ?: "") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Set $setNumber:",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier.width(50.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .height(56.dp),
            value = weightText,
            onValueChange = { value ->
                weightText = value
                onValueChange(value.toDoubleOrNull(), repsText.toIntOrNull())
            },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            value = repsText,
            onValueChange = { value ->
                repsText = value
                onValueChange(weightText.toDoubleOrNull(), value.toIntOrNull())
            },
            label = { Text("Reps") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
}