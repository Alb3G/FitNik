package com.example.fitnik.settings.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadUserData()
    }

    fun onLogOut() {
        viewModelScope.launch {
            userPreferencesManager.clearUserId()
            authRepository.signOut()
        }
    }

    private fun loadUserData() {
        Log.d("SettingsViewModel", "Starting to load user data")
        viewModelScope.launch {
            try {
                Log.d("SettingsViewModel", "Collecting user ID from preferences")
                userPreferencesManager.getUserId().collect { userId ->
                    Log.d("SettingsViewModel", "User ID collected: $userId")
                    if (userId != null) {
                        Log.d("SettingsViewModel", "Fetching user details for ID: $userId")
                        val user = userRepository.getUserById(userId)
                        Log.d("SettingsViewModel", "User retrieved: $user")
                        _uiState.update { it.copy(user = user, isLoading = false) }
                    } else {
                        Log.e("SettingsViewModel", "Error: User ID is null")
                        _uiState.update { it.copy(error = "User ID not found", isLoading = false) }
                    }
                }
            } catch (e: Exception) {
                Log.e("SettingsViewModel", "Exception during user data loading", e)
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

}