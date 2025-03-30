package com.example.fitnik.authentication.domain.usecase

class ValidateNameUseCase {

    operator fun invoke(name: String): Boolean {
        val regex = Regex("^[a-zA-ZÀ-ÿ\\s]{1,150}\$")
        return name.matches(regex)
    }

}