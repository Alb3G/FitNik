package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.fitnik.core.data.local.entity.UserEntity

@Dao
interface UserDAO {

    @Query("SELECT * FROM users where id = :uid;")
    suspend fun findUserById(uid: String): UserEntity?

    @Insert(onConflict = REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity): Int

}