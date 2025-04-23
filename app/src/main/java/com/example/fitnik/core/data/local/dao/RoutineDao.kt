package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routines")
    fun getAllRoutines(): Flow<List<RoutineEntity>>

}