package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSetDao {

    @Query("SELECT * FROM workoutsetentity WHERE exerciseId = :exerciseId")
    fun getSetsByExerciseId(exerciseId: String): Flow<List<WorkoutSetEntity>>

    @Insert(onConflict = REPLACE)
    fun save(workoutSetEntity: WorkoutSetEntity): Long

    @Delete
    fun delete(workoutSetEntity: WorkoutSetEntity)

}