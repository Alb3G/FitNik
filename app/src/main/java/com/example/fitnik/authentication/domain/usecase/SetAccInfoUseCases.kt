package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.core.domain.usecase.GetRoomUserUseCase
import com.example.fitnik.core.domain.usecase.UpdateRoomUserUseCase

data class SetAccInfoUseCases(
    val convertWeightUseCase: ConvertWeightUseCase,
    val convertHeightUseCase: ConvertHeightUseCase,
    val getUserAgeUseCase: GetUserAgeUseCase,
    val updateUserFromFirestoreUseCase: UpdateUserFromFirestoreUseCase,
    val getRoomUserUseCase: GetRoomUserUseCase,
    val updateRoomUserUseCase: UpdateRoomUserUseCase
)
