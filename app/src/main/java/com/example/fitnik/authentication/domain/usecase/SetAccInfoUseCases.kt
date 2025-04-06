package com.example.fitnik.authentication.domain.usecase

data class SetAccInfoUseCases(
    val convertWeightUseCase: ConvertWeightUseCase,
    val convertHeightUseCase: ConvertHeightUseCase,
    val getUserAgeUseCase: GetUserAgeUseCase,
    val updateUserFromFirestoreUseCase: UpdateUserFromFirestoreUseCase
)
