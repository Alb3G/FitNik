package com.example.fitnik.authentication.domain.usecase

class ConvertWeightUseCase {

    operator fun invoke(weight: Double, isKgUnit: Boolean): Double {
        val oneKgInLbs = 2.2046226218
        val oneLbInKgs = 0.45359237
        return if (isKgUnit) {
            weight * oneKgInLbs
        } else {
            weight * oneLbInKgs
        }
    }

}