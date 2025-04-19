package com.example.fitnik.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.core.domain.model.TrainingMockProvider
import com.example.fitnik.core.domain.usecase.GetUserWorkoutsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserWorkoutsUseCase: GetUserWorkoutsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadUserWorkouts()
    }

    fun updateSelectedIndex(index: Int) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(selectedItemIndex = index)
            }
        }
    }

    fun loadUserWorkouts() {
        val workout = TrainingMockProvider.getMuscleGainTraining()
        viewModelScope.launch {
            _state.update {
                it.copy(workouts = listOf(workout, workout, workout))
            }
        }
    }
}