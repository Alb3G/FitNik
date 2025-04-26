package com.example.fitnik.core.data.local

import com.example.fitnik.core.data.local.entity.ExerciseEntity
import com.example.fitnik.core.data.local.entity.RoutineEntity
import com.example.fitnik.core.data.local.entity.WorkoutEntity
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity
import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import java.util.UUID

object FakeDB {

    val fakeRoutine = Routine(
        id = UUID.randomUUID().toString(),
        name = "3x Workout Routine",
        workouts = listOf(
            Workout(
                id = UUID.randomUUID().toString(),
                name = "Chest workout",
                exercises = listOf(
                    Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Bench Press",
                        sets = listOf(
                            WorkoutSet(
                                weight = 40.0,
                                reps = 5
                            ),
                            WorkoutSet(
                                weight = 20.0,
                                reps = 8
                            ),
                            WorkoutSet(
                                weight = 15.0,
                                reps = 12
                            )
                        )
                    )
                )
            ),
            Workout(
                id = UUID.randomUUID().toString(),
                name = "Legs Workout",
                exercises = listOf(
                    Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Squat",
                        sets = listOf(
                            WorkoutSet(
                                weight = 40.0,
                                reps = 5
                            ),
                            WorkoutSet(
                                weight = 20.0,
                                reps = 8
                            ),
                            WorkoutSet(
                                weight = 15.0,
                                reps = 12
                            )
                        )
                    )
                )
            )
        )
    )

    val fakeRoutineEntity = RoutineEntity("routineId", "3x Routine")

    val fakeWorkoutA = WorkoutEntity("workoutaId", "Workout A", "routineId")
    val fakeExercise1 = ExerciseEntity("ex1", "Bench Press", "workoutaId")
    val workoutSet11 = WorkoutSetEntity(1, 80.5, 8, "ex1")
    val workoutSet12 = WorkoutSetEntity(2, 80.5, 6, "ex1")
    val fakeExercise2 = ExerciseEntity("ex2", "Cable Flies", "workoutaId")
    val workoutSet21 = WorkoutSetEntity(1, 32.5, 8, "ex2")
    val workoutSet22 = WorkoutSetEntity(2, 80.5, 6, "ex2")

    val fakeWorkoutB = WorkoutEntity("workoutbId", "Workout B", "routineId")
    val fakeExercise3 = ExerciseEntity("ex3", "Squats", "workoutbId")
    val workoutSet31 = WorkoutSetEntity(1, 80.5, 8, "ex3")
    val workoutSet32 = WorkoutSetEntity(2, 80.5, 6, "ex3")
    val fakeExercise4 = ExerciseEntity("ex4", "Deadlifts", "workoutbId")
    val workoutSet41 = WorkoutSetEntity(1, 80.5, 8, "ex4")
    val workoutSet42 = WorkoutSetEntity(2, 80.5, 6, "ex4")

}