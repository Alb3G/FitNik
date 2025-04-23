package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitnik.core.data.local.entity.WorkoutEntity

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun findById(id: String): WorkoutEntity

}