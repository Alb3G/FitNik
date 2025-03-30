package com.example.fitnik.authentication.domain.usecase

import com.example.fitnik.authentication.domain.matcher.EmailMatcher

/**
 * Extraccion de validaciones a useCases para mejor testeo.
 * Email matcher viene como interfaz porque Patterns.EMAIL_ADDRESS.matcher(email).matches()
 * es una validacion de android y en domain no deberia haber nada de codigo android
 * para poder ejecutar test unitarios sin emulador.
 */
class ValidateEmailUseCase(
    private val emailMatcher: EmailMatcher
) {

    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }

}