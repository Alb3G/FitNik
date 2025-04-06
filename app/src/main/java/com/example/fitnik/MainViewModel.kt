package com.example.fitnik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.GetUserIdUseCase
import com.example.fitnik.authentication.domain.usecase.UserAccountIsCompleted
import com.example.fitnik.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val userAccountIsCompleted: UserAccountIsCompleted,
    private val repository: AuthRepository
): ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
        private set

    var accIsCompleted by mutableStateOf(false)
        private set

    fun checkSessionState() {
        viewModelScope.launch {
            val uid = getUserIdUseCase()
            isLoggedIn = uid != null
            accIsCompleted = if (uid != null) userAccountIsCompleted() else false
            isLoading = false
        }
    }


    fun accCompletedCheck() {
        viewModelScope.launch {
            accIsCompleted = userAccountIsCompleted()
        }
    }
}