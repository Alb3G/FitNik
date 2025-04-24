package com.example.fitnik.home.domain.repository

import com.example.fitnik.core.domain.model.Routine
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllRoutines(): Flow<List<Routine>>
}