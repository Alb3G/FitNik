package com.example.fitnik.settings.presentation

import com.example.fitnik.core.domain.model.User

data class SettingsUiState(
    val user: User? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)