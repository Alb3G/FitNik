package com.example.fitnik.home.data


import com.example.fitnik.core.data.local.FakeDB
import com.example.fitnik.core.domain.mappers.toDomain
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl : HomeRepository {

    override fun getAllRoutines(): Flow<List<Routine>> {
        val routine = FakeDB.fakeRoutineEntity.toDomain()
        return flowOf(listOf(routine))
    }

}