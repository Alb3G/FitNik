package com.example.fitnik.home.data

import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import com.example.fitnik.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class HomeRepositoryImpl : HomeRepository {
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

    override fun getAllRoutines(): Flow<List<Routine>> {
        return flowOf(listOf(fakeRoutine))
    }

}