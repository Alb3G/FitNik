package com.example.fitnik.routineDetail.data.repository

import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.routineDetail.domain.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class RoutineRepositoryImpl : RoutineRepository {

    override fun getAllWorkouts(): Flow<List<Workout>> {
        TODO("Not yet implemented")
    }

}