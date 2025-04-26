package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId")
    fun getExercisesByWorkoutId(workoutId: String): Flow<List<ExerciseEntity>>

    @Insert(onConflict = REPLACE)
    fun save(exerciseEntity: ExerciseEntity): Long

    @Delete
    fun delete(exerciseEntity: ExerciseEntity)

}