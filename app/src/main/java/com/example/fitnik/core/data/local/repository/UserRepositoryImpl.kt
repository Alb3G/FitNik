package com.example.fitnik.core.data.local.repository

import com.example.fitnik.core.data.local.dao.ExerciseDao
import com.example.fitnik.core.data.local.dao.UserDAO
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.core.data.local.dao.WorkoutSetDao
import com.example.fitnik.core.domain.mappers.toDomain
import com.example.fitnik.core.domain.mappers.toEntity
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.repository.UserRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDAO: UserDAO,
    private val workoutDao: WorkoutDao,
    private val exerciseDao: ExerciseDao,
    private val workoutSetDao: WorkoutSetDao
): UserRepository {
    override suspend fun saveUser(user: User) {
        userDAO.saveUser(user.toEntity())
    }

    override suspend fun getUserById(userId: String): User? {
        val user = userDAO.findUserById(userId) ?: return null
        return user.toDomain()
    }

    override suspend fun updateUser(user: User): Boolean {
        return userDAO.updateUser(user.toEntity()) > 0
    }

    override suspend fun userAccIsComplete(user: User): Boolean {
        return user.toEntity().accIscomplete
    }

    override suspend fun getAllUserRoutines(userId: String): Flow<List<Routine>> {
        return userDAO.getAllUserRoutines(userId).map { routineEntities ->
            // Filter out null routines and map each to domain model
            routineEntities.filterNotNull().map { routineEntity ->
                // For each routine, get its workouts
                val workoutFlow = workoutDao.getAllWorkouts(routineEntity.routineId)
                    .map { workoutEntities ->
                        workoutEntities.map { workoutEntity ->
                            // Map each workout to domain model with exercises
                            val exercises = exerciseDao.getExercisesByWorkoutId(workoutEntity.workoutId)
                                .first()
                                .map { exerciseEntity ->
                                    // For each exercise, get its sets
                                    val sets = workoutSetDao.getSetsByExerciseId(exerciseEntity.exerciseId)
                                        .first()
                                        .map { it.toDomain() }

                                    // Map exercise with its sets to domain model
                                    exerciseEntity.toDomain(sets)
                                }
                            // Map workout with its exercises to domain model
                            workoutEntity.toDomain(exercises)
                        }
                    }
                // Convert routine entity to domain model with workouts
                routineEntity.toDomain(workoutFlow.first())
            }
        }
    }
}