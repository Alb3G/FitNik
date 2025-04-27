package com.example.fitnik.home.di

import com.example.fitnik.core.data.local.dao.RoutineDao
import com.example.fitnik.core.data.local.dao.WorkoutDao
import com.example.fitnik.home.data.HomeRepositoryImpl
import com.example.fitnik.home.domain.repository.HomeRepository
import com.example.fitnik.home.domain.usecase.GetRoutinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        routineDao: RoutineDao,
        workoutDao: WorkoutDao
    ): HomeRepository =
        HomeRepositoryImpl(routineDao = routineDao, workoutDao = workoutDao)


    @Provides
    @Singleton
    fun provideGetRoutineUseCase(homeRepository: HomeRepository) =
        GetRoutinesUseCase(homeRepository)

}