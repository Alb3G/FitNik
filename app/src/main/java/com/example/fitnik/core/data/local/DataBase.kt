package com.example.fitnik.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnik.core.data.local.dao.ExerciseDao
import com.example.fitnik.core.data.local.dao.RoutineDao
import com.example.fitnik.core.data.local.dao.UserDAO
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.core.data.local.dao.WorkoutLogDao
import com.example.fitnik.core.data.local.dao.WorkoutSetDao
import com.example.fitnik.core.data.local.entity.ExerciseEntity
import com.example.fitnik.core.data.local.entity.RoutineEntity
import com.example.fitnik.core.data.local.entity.UserEntity
import com.example.fitnik.core.data.local.entity.WorkoutEntity
import com.example.fitnik.core.data.local.entity.WorkoutLogEntity
import com.example.fitnik.core.data.local.entity.WorkoutSetEntity

@Database(
    entities = [
        UserEntity::class,
        RoutineEntity::class,
        WorkoutEntity::class,
        WorkoutLogEntity::class,
        ExerciseEntity::class,
        WorkoutSetEntity::class
    ],
    version = 1,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun workoutDAO(): WorkoutDao
    abstract fun workoutLogDAO(): WorkoutLogDao
    abstract fun routineDAO(): RoutineDao
    abstract fun exerciseDAO(): ExerciseDao
    abstract fun workoutSetDAO(): WorkoutSetDao
}