package com.example.fitnik.core.di

import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.repository.UserRepository
import com.example.fitnik.core.domain.usecase.GetUserWorkoutsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun provideGetUserWorkoutsUseCase(
        userPreferencesManager: UserPreferencesManager,
        userRepository: UserRepository
    ): GetUserWorkoutsUseCase {
        return GetUserWorkoutsUseCase(userPreferencesManager, userRepository)
    }

}