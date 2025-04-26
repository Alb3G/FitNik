package com.example.fitnik.routineDetail.data.repository

import com.example.fitnik.core.data.local.dao.ExerciseDao
import com.example.fitnik.core.data.local.dao.RoutineDao
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.core.data.local.dao.WorkoutSetDao
import com.example.fitnik.core.data.local.entity.ExerciseEntity
import com.example.fitnik.core.data.local.entity.RoutineEntity
import com.example.fitnik.core.data.local.entity.WorkoutEntity
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity
import com.example.fitnik.core.domain.mappers.toDomain
import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import com.example.fitnik.routineDetail.domain.RoutineRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class RoutineRepositoryImpl @Inject constructor(
    private val routineDao: RoutineDao,
    private val workoutDao: WorkoutDao,
    private val exerciseDao: ExerciseDao,
    private val workoutSetDao: WorkoutSetDao
) : RoutineRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllRoutineWorkouts(routineId: String): Flow<List<Workout>> {
        return workoutDao.getAllWorkouts(routineId).map { workoutEntities ->
            coroutineScope {
                workoutEntities.map { workoutEntity ->
                    async {
                        val exercises = getExercisesForWorkout(workoutEntity.workoutId)
                        workoutEntity.toDomain(exercises)
                    }
                }.awaitAll()
            }
        }
    }

    override suspend fun createRoutine(routine: Routine): String = withContext(Dispatchers.IO) {
        val routineEntity = RoutineEntity(
            routineId = routine.id,
            name = routine.name
        )

        val routineId = routineDao.save(routineEntity).toString()

        routine.workouts.forEach { workout ->
            createWorkout(workout, routineId)
        }

        routineId
    }

    override suspend fun createWorkout(
        workout: Workout,
        routineId: String
    ): String = withContext(Dispatchers.IO) {
        val workoutEntity = WorkoutEntity(
            workoutId = workout.id,
            name = workout.name,
            routineId = routineId
        )

        val workoutId = workoutDao.save(workoutEntity).toString()

        workout.exercises.forEach { exercise ->
            createExercise(exercise, workoutId)
        }

        workoutId
    }

    override suspend fun createExercise(
        exercise: Exercise,
        workoutId: String
    ): String = withContext(Dispatchers.IO) {
        val exerciseEntity = ExerciseEntity(
            exerciseId = exercise.id,
            name = exercise.name,
            workoutId = workoutId
        )

        val exerciseId = exerciseDao.save(exerciseEntity).toString()

        exercise.sets.forEach { set ->
            createSet(set, exerciseId)
        }

        exerciseId
    }

    override suspend fun createSet(
        set: WorkoutSet,
        exerciseId: String
    ): Long = withContext(Dispatchers.IO) {
        val setEntity = WorkoutSetEntity(
            weight = set.weight ?: 0.0,
            reps = set.reps ?: 0,
            exerciseId = exerciseId
        )

        workoutSetDao.save(setEntity)
    }

    private suspend fun getExercisesForWorkout(workoutId: String): List<Exercise> {
        return exerciseDao.getExercisesByWorkoutId(workoutId)
            .first() // Convierte Flow a una lista una sola vez
            .map { exerciseEntity ->
                val sets = getSetsForExercise(exerciseEntity.exerciseId)
                exerciseEntity.toDomain(sets)
            }
    }

    private suspend fun getSetsForExercise(exerciseId: String): List<WorkoutSet> {
        return workoutSetDao.getSetsByExerciseId(exerciseId)
            .first()
            .map { it.toDomain() }
    }
}