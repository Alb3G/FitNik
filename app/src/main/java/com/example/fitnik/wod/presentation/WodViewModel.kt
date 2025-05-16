package com.example.fitnik.wod.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WodViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(WodState())
    val state: StateFlow<WodState> = _state.asStateFlow()

    fun onEvent(event: WodEvent) {
        when (event) {
            is WodEvent.CompleteWod -> {  }
            is WodEvent.StartWod -> {  }
            is WodEvent.NextPeriod -> {  }
            is WodEvent.PreviousPeriod -> {  }
        }
    }

}