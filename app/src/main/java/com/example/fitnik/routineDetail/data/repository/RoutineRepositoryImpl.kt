package com.example.fitnik.routineDetail.data.repository

import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.core.data.local.dao.ExerciseDao
import com.example.fitnik.core.data.local.dao.RoutineDao
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.core.data.local.dao.WorkoutSetDao
import com.example.fitnik.core.data.local.entity.ExerciseEntity
import com.example.fitnik.core.data.local.entity.RoutineEntity
import com.example.fitnik.core.data.local.entity.WorkoutEntity
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity
import com.example.fitnik.core.domain.mappers.toDomain
import com.example.fitnik.core.domain.mappers.toEntity
import com.example.fitnik.core.domain.model.Exercise
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.WorkoutSet
import com.example.fitnik.routineDetail.domain.RoutineRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class RoutineRepositoryImpl @Inject constructor(
    private val routineDao: RoutineDao,
    private val workoutDao: WorkoutDao,
    private val exerciseDao: ExerciseDao,
    private val workoutSetDao: WorkoutSetDao,
    private val getUserIdUseCase: GetUserIdUseCase
) : RoutineRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllRoutineWorkouts(routineId: String): Flow<List<Workout>> {
        return workoutDao.getAllWorkouts(routineId).flatMapLatest { workoutEntities ->
            if (workoutEntities.isEmpty()) {
                return@flatMapLatest flow { emit(emptyList<Workout>()) }
            }

            val workoutFlows = workoutEntities.map { workoutEntity ->
                exerciseDao.getExercisesByWorkoutId(workoutEntity.workoutId).map { exerciseEntities ->

                    val exercises = exerciseEntities.map { exerciseEntity ->
                        val sets = workoutSetDao.getSetsByExerciseId(exerciseEntity.exerciseId).first()

                        exerciseEntity.toDomain(sets.map { it.toDomain() })
                    }

                    workoutEntity.toDomain(exercises)
                }
            }

            if (workoutFlows.isEmpty()) {
                return@flatMapLatest flow { emit(emptyList<Workout>()) }
            }

            combine(workoutFlows) { workouts -> workouts.toList() }
        }
    }

    override suspend fun createRoutine(routine: Routine): String = withContext(Dispatchers.IO) {
        val routineEntity = RoutineEntity(
            routineId = routine.id,
            name = routine.name,
            userId = getUserIdUseCase() ?: "" // Ojo con algun fallo aqui.
        )

        val routineId = routine.id

        routineDao.save(routineEntity)

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

        val workoutId = workout.id

        workoutDao.save(workoutEntity)

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

        val exerciseId = exercise.id
        exerciseDao.save(exerciseEntity)

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
            workoutSetId = set.id,
            weight = set.weight ?: 0.0,
            reps = set.reps ?: 0,
            exerciseId = exerciseId
        )

        val setId = workoutSetDao.save(setEntity)

        setId
    }

    override suspend fun updateSetsForExercise(
        exerciseId: String,
        sets: List<WorkoutSet>
    ) {
        withContext(Dispatchers.IO) {
            val workoutSetEntities = sets.map {
                it.toEntity(exerciseId)
            }
            workoutSetDao.updateSetsForExercise(exerciseId, workoutSetEntities)
        }
    }

    override suspend fun deleteRoutineById(routineId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            // Primero verificamos que la rutina exista
            val routineEntity = routineDao.getRoutineById(routineId).first()

            if (routineEntity == null) {
                return@withContext Result.failure(NoSuchElementException("Routine with ID $routineId not found"))
            }

            // Ejecutamos la eliminación en cascada dentro de una transacción
            deleteRoutineWithRelations(routineEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun deleteRoutineWithRelations(routineEntity: RoutineEntity) {
        // Obtenemos primero todos los IDs de workouts asociados a esta rutina
        val workoutEntities = workoutDao.getAllWorkouts(routineEntity.routineId).first()

        // Para cada workout, eliminamos sus ejercicios y sets
        workoutEntities.forEach { workout ->
            val exercises = exerciseDao.getExercisesByWorkoutId(workout.workoutId).first()

            // Para cada ejercicio, eliminamos sus sets
            exercises.forEach { exercise ->
                workoutSetDao.deleteSetsByExerciseId(exercise.exerciseId)
            }

            // Eliminamos todos los ejercicios de este workout
            if (exercises.isNotEmpty()) {
                exerciseDao.deleteAll(exercises)
            }
        }

        // Eliminamos todos los workouts
        if (workoutEntities.isNotEmpty()) {
            workoutDao.deleteAll(workoutEntities)
        }

        // Finalmente eliminamos la rutina
        routineDao.delete(routineEntity)
    }
}