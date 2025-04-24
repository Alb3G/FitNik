package com.example.fitnik.home.di

import com.example.fitnik.home.data.HomeRepositoryImpl
import com.example.fitnik.home.domain.repository.HomeRepository
import com.example.fitnik.home.domain.usecase.GetRoutineUseCase
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
    fun provideHomeRepository(): HomeRepository =
        HomeRepositoryImpl()


    @Provides
    @Singleton
    fun provideGetRoutineUseCase(homeRepository: HomeRepository) =
        GetRoutineUseCase(homeRepository)

}