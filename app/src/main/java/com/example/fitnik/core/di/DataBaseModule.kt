package com.example.fitnik.core.di

import android.content.Context
import androidx.room.Room
import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.core.data.local.AppDataBase
import com.example.fitnik.core.data.local.dao.ExerciseDao
import com.example.fitnik.core.data.local.dao.RoutineDao
import com.example.fitnik.core.data.local.dao.UserDAO
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.core.data.local.dao.WorkoutSetDao
import com.example.fitnik.core.data.local.repository.UserRepositoryImpl
import com.example.fitnik.core.domain.repository.UserRepository
import com.example.fitnik.routineDetail.data.repository.RoutineRepositoryImpl
import com.example.fitnik.routineDetail.domain.RoutineRepository
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
    @Singleton
    fun provideUserDAO(db: AppDataBase): UserDAO = db.userDAO()

    @Provides
    @Singleton
    fun provideWorkoutDAO(db: AppDataBase): WorkoutDao = db.workoutDAO()

    @Provides
    @Singleton
    fun provideRoutineDAO(db: AppDataBase): RoutineDao = db.routineDAO()

    @Provides
    @Singleton
    fun provideWorkoutSetDAO(db: AppDataBase): WorkoutSetDao = db.workoutSetDAO()

    @Provides
    @Singleton
    fun provideExerciseDAO(db: AppDataBase): ExerciseDao = db.exerciseDAO()

    @Provides
    @Singleton
    fun provideUserRepository(
        userDAO: UserDAO,
        workoutDao: WorkoutDao,
        exerciseDao: ExerciseDao,
        workoutSetDao: WorkoutSetDao
    ): UserRepository {
        return UserRepositoryImpl(userDAO, workoutDao, exerciseDao, workoutSetDao)
    }

    @Provides
    @Singleton
    fun provideRoutineRepository(
        routineDao: RoutineDao,
        workoutDao: WorkoutDao,
        exerciseDao: ExerciseDao,
        workoutSetDao: WorkoutSetDao,
        getUserIdUseCase: GetUserIdUseCase
    ): RoutineRepository {
        return RoutineRepositoryImpl(routineDao,workoutDao,exerciseDao,workoutSetDao, getUserIdUseCase)
    }
}
