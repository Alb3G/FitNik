package com.example.fitnik.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager,
    private val authRepository: AuthRepository
): ViewModel() {

    fun onLogOut() {
        viewModelScope.launch {
            userPreferencesManager.clearUserId()
            authRepository.signOut()
        }
    }

}