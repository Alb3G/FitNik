package com.example.fitnik.authentication.di

import com.example.fitnik.authentication.data.matcher.EmailMatcherImpl
import com.example.fitnik.authentication.data.repository.AuthRepositoryImpl
import com.example.fitnik.authentication.domain.matcher.EmailMatcher
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.ConvertHeightUseCase
import com.example.fitnik.authentication.domain.usecase.ConvertWeightUseCase
import com.example.fitnik.authentication.domain.usecase.GetUserAgeUseCase
import com.example.fitnik.authentication.domain.usecase.GetUserFromFirestoreUseCase
import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.authentication.domain.usecase.LoginUseCases
import com.example.fitnik.authentication.domain.usecase.LoginWithEmailUseCase
import com.example.fitnik.authentication.domain.usecase.LoginWithGoogleCredentialUseCase
import com.example.fitnik.authentication.domain.usecase.SaveUserInFireStoreUseCase
import com.example.fitnik.authentication.domain.usecase.SetAccInfoUseCases
import com.example.fitnik.authentication.domain.usecase.SignUpUseCase
import com.example.fitnik.authentication.domain.usecase.SignUpUseCases
import com.example.fitnik.authentication.domain.usecase.UpdateUserFromFirestoreUseCase
import com.example.fitnik.authentication.domain.usecase.UserAccountIsCompletedUseCase
import com.example.fitnik.authentication.domain.usecase.ValidateEmailUseCase
import com.example.fitnik.authentication.domain.usecase.ValidateNameUseCase
import com.example.fitnik.authentication.domain.usecase.ValidatePasswordUseCase
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            loginWithGoogleCredentialUseCase = LoginWithGoogleCredentialUseCase(repository),
            getUserFromFirestoreUseCase = GetUserFromFirestoreUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        authRepository: AuthRepository,
        localUserRepository: UserRepository,
        userPreferencesManager: UserPreferencesManager,
        emailMatcher: EmailMatcher
    ): SignUpUseCases {
        return SignUpUseCases(
            validateNameUseCase = ValidateNameUseCase(),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            signUpUseCase = SignUpUseCase(authRepository, localUserRepository, userPreferencesManager),
            saveUserInFireStoreUseCase = SaveUserInFireStoreUseCase(authRepository)
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(
        userPreferencesManager: UserPreferencesManager
    ): GetUserIdUseCase {
        return GetUserIdUseCase(userPreferencesManager)
    }

    @Provides
    @Singleton
    fun provideUserAccountIsCompleted(
        repository: AuthRepository,
        localUserRepository: UserRepository
    ): UserAccountIsCompletedUseCase {
        return UserAccountIsCompletedUseCase(repository, localUserRepository)
    }

    @Provides
    @Singleton
    fun provideSetAccInfoUseCases(
        repository: AuthRepository
    ): SetAccInfoUseCases {
        return SetAccInfoUseCases(
            convertWeightUseCase = ConvertWeightUseCase(),
            convertHeightUseCase = ConvertHeightUseCase(),
            getUserAgeUseCase = GetUserAgeUseCase(),
            updateUserFromFirestoreUseCase = UpdateUserFromFirestoreUseCase(repository)
        )
    }
}