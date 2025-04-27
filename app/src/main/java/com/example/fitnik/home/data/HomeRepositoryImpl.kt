package com.example.fitnik.home.data


import com.example.fitnik.core.data.local.dao.RoutineDao
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.core.domain.mappers.toDomain
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl(
    private val routineDao: RoutineDao,
    private val workoutDao: WorkoutDao
) : HomeRepository {

    override fun getAllRoutines(): Flow<List<Routine>> {
        return routineDao.getAllRoutines().map { routineEntities ->
            routineEntities.map { routineEntity ->
                val workoutsFlow = workoutDao.getAllWorkouts(routineEntity.routineId)
                    .map { workoutEntities ->
                        workoutEntities.map { workoutEntity ->
                            workoutEntity.toDomain()
                        }
                    }
                // Initially return the Routine with empty workouts list
                routineEntity.toDomain(workoutsFlow.first())
            }
        }
    }
}