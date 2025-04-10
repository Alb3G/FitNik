package com.example.fitnik.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnik.core.data.local.dao.UserDAO
import com.example.fitnik.core.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}