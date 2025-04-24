package com.example.fitnik.home.domain.usecase

import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetRoutineUseCase(
    private val homeRepository: HomeRepository
) {

    operator fun invoke(): Flow<List<Routine>> {
        return homeRepository.getAllRoutines()
    }

}