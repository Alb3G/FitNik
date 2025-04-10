package com.example.fitnik.core.di

import android.content.Context
import androidx.room.Room
import com.example.fitnik.core.data.local.AppDataBase
import com.example.fitnik.core.data.local.dao.UserDAO
import com.example.fitnik.core.data.local.repository.UserRepositoryImpl
import com.example.fitnik.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "fitnik_app.db"
        ).build()
    }

    @Provides
    fun provideUserDAO(db: AppDataBase): UserDAO = db.userDAO()

    @Provides
    @Singleton
    fun provideUserRepository(userDAO: UserDAO): UserRepository {
        return UserRepositoryImpl(userDAO)
    }
}