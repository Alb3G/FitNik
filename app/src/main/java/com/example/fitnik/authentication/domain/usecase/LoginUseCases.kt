package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.core.domain.usecase.SaveRoomUserUseCase

data class LoginUseCases(
    val loginWithEmailUseCase: LoginWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val loginWithGoogleCredentialUseCase: LoginWithGoogleCredentialUseCase,
    val getUserFromFirestoreUseCase: GetUserFromFirestoreUseCase,
    val saveRoomUserUseCase: SaveRoomUserUseCase
)
