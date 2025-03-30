package com.example.fitnik.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}