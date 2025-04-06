package com.example.fitnik.authentication.di

import com.example.fitnik.authentication.data.matcher.EmailMatcherImpl
import com.example.fitnik.authentication.data.repository.AuthRepositoryImpl
import com.example.fitnik.authentication.domain.matcher.EmailMatcher
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.ConvertHeightUseCase
import com.example.fitnik.authentication.domain.usecase.ConvertWeightUseCase
import com.example.fitnik.authentication.domain.usecase.GetUserAgeUseCase
import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.authentication.domain.usecase.LoginUseCases
import com.example.fitnik.authentication.domain.usecase.LoginWithEmailUseCase
import com.example.fitnik.authentication.domain.usecase.LoginWithGoogleCredentialUseCase
import com.example.fitnik.authentication.domain.usecase.SaveUserInFireStoreUseCase
import com.example.fitnik.authentication.domain.usecase.SetAccInfoUseCases
import com.example.fitnik.authentication.domain.usecase.SignUpUseCase
import com.example.fitnik.authentication.domain.usecase.SignUpUseCases
import com.example.fitnik.authentication.domain.usecase.UpdateUserFromFirestoreUseCase
import com.example.fitnik.authentication.domain.usecase.UserAccountIsCompleted
import com.example.fitnik.authentication.domain.usecase.ValidateEmailUseCase
import com.example.fitnik.authentication.domain.usecase.ValidateNameUseCase
import com.example.fitnik.authentication.domain.usecase.ValidatePasswordUseCase
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
        )
    }

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repository: AuthRepository,
        emailMatcher: EmailMatcher
    ): SignUpUseCases {
        return SignUpUseCases(
            validateNameUseCase = ValidateNameUseCase(),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            signUpUseCase = SignUpUseCase(repository),
            saveUserInFireStoreUseCase = SaveUserInFireStoreUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUserAccountIsCompleted(repository: AuthRepository): UserAccountIsCompleted {
        return UserAccountIsCompleted(repository)
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