package com.example.fitnik.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.fitnik.core.data.local.entity.RoutineEntity
import com.example.fitnik.core.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM users where id = :uid;")
    suspend fun findUserById(uid: String): UserEntity?

    @Insert(onConflict = REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("SELECT * FROM routines WHERE userId = :userId")
    fun getAllUserRoutines(userId: String): Flow<List<RoutineEntity?>>

}