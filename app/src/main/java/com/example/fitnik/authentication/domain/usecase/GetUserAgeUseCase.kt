package com.example.fitnik.authentication.domain.usecase

import java.time.LocalDate
import java.time.Period

class GetUserAgeUseCase {

    operator fun invoke(birthDate: LocalDate): Int {
        return Period.between(
            LocalDate.of(birthDate.year, birthDate.month, birthDate.dayOfMonth),
            LocalDate.now()
        ).years
    }

}