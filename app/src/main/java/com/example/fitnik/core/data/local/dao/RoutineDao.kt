package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routines")
    fun getAllRoutines(): Flow<List<RoutineEntity>>

    @Query("SELECT * FROM routines WHERE routineId = :id")
    fun getRoutineById(id: String): Flow<RoutineEntity?>

    @Insert(onConflict = REPLACE)
    fun save(routineEntity: RoutineEntity): Long

    @Delete
    fun delete(routineEntity: RoutineEntity)

}