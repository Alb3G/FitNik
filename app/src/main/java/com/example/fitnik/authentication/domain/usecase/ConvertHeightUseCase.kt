package com.example.fitnik.authentication.domain.usecase

class ConvertHeightUseCase {

    operator fun invoke(height: Double, isCmUnit: Boolean): Double {
        val oneCmInFt = 0.0328084
        return if (isCmUnit) {
            height
        } else {
            height * oneCmInFt
        }
    }

}