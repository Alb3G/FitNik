package com.example.fitnik.authentication.domain.usecase

data class SetAccInfoUseCases(
    val convertWeightUseCase: ConvertWeightUseCase,
    val getUserAgeUseCase: GetUserAgeUseCase
)
