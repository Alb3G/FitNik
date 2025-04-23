package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.WorkoutLogEntity

@Dao
interface WorkoutLogDao {

    @Query("SELECT * FROM workoutlogentity WHERE id = :id")
    fun findById(id: String): WorkoutLogEntity

    @Insert(onConflict = REPLACE)
    fun save(workoutLogEntity: WorkoutLogEntity)

    @Delete
    fun delete(workoutLogEntity: WorkoutLogEntity)

}