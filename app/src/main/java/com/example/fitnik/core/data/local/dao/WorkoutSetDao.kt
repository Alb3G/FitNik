package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSetDao {

    @Query("SELECT * FROM workoutsetentity WHERE exerciseId = :exerciseId")
    fun getSetsByExerciseId(exerciseId: String): Flow<List<WorkoutSetEntity>>

    @Query("DELETE FROM workoutsetentity WHERE exerciseId = :exerciseId")
    suspend fun deleteSetsByExerciseId(exerciseId: String)

    @Insert(onConflict = REPLACE)
    fun save(workoutSetEntity: WorkoutSetEntity): Long

    @Insert(onConflict = REPLACE)
    fun save(sets: List<WorkoutSetEntity>)

    @Delete
    fun delete(workoutSetEntity: WorkoutSetEntity)

    @Transaction
    suspend fun updateSetsForExercise(exerciseId: String, sets: List<WorkoutSetEntity>) {
        deleteSetsByExerciseId(exerciseId)
        save(sets)
    }
}