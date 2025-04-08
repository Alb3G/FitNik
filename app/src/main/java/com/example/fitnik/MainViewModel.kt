package com.example.fitnik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.NavigationState.Loading
import com.example.fitnik.NavigationState.LoggedInComplete
import com.example.fitnik.NavigationState.LoggedInIncomplete
import com.example.fitnik.NavigationState.NeedsOnboarding
import com.example.fitnik.NavigationState.NotLoggedIn
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.authentication.domain.usecase.UserAccountIsCompletedUseCase
import com.example.fitnik.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val userAccountIsCompleted: UserAccountIsCompletedUseCase,
    private val repository: AuthRepository
): ViewModel() {

    private val _navigationState = MutableStateFlow<NavigationState>(Loading)
    val navigationState = _navigationState.asStateFlow()

    fun checkSessionState() {
        viewModelScope.launch {
            val uid = getUserIdUseCase()
            val hasSeenOnboarding = hasSeenOnboardingUseCase()
            _navigationState.value = when {
                uid == null -> { if (hasSeenOnboarding) NotLoggedIn else NeedsOnboarding }
                else -> {
                    val completed = userAccountIsCompleted()
                    if (completed) LoggedInComplete else LoggedInIncomplete
                }
            }
        }
    }
}

sealed class NavigationState {
    object Loading : NavigationState()
    object NeedsOnboarding : NavigationState()
    object NotLoggedIn : NavigationState()
    object LoggedInIncomplete : NavigationState()
    object LoggedInComplete : NavigationState()
}