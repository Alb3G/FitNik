package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE workoutId = :id")
    fun findById(id: String): WorkoutEntity

    @Query("SELECT * FROM workouts where routineId = :routineId ")
    fun getAllWorkouts(routineId: String): Flow<List<WorkoutEntity>>

    @Insert(onConflict = REPLACE)
    fun save(workoutEntity: WorkoutEntity)

    @Delete
    fun delete(workoutEntity: WorkoutEntity)
}