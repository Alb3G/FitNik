package com.example.fitnik.authentication.domain.usecase

class ConvertWeightUseCase {

    operator fun invoke(weight: Double, isKgUnit: Boolean): Double {
        val oneKgInLbs = 2.2046226218
        return if (isKgUnit) {
            weight // Devolvemos peso en KG
        } else {
            weight * oneKgInLbs // Lo convertimos a LB
        }
    }

}