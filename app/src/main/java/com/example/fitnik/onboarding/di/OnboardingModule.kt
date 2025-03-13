package com.example.fitnik.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.example.fitnik.onboarding.data.repository.OnboardingRepositoryImpl
import com.example.fitnik.onboarding.domain.repository.OnboardingRepository
import com.example.fitnik.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.example.fitnik.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @InstallIn(SingletonComponent::class) nos dice que las dependencias que se instancien
 * en nuestro singleton viviran mientras la app este en ejecucion.
 * Util para objetos globales como sharedPreferences o Repositorios
 */
@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Provides // Indica a hilt que va a crear una instancia de sharedPreferences.
    @Singleton // Asegura que solo exista una sola instancia en toda la app.
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("fitnik_onboarding_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun hasSeenOnboardingUseCase(repository: OnboardingRepository) = HasSeenOnboardingUseCase(repository)

    @Provides
    @Singleton
    fun completeOnboardingUseCase(repository: OnboardingRepository) = CompleteOnboardingUseCase(repository)
}