package com.example.fitnik.authentication.presentation.setUpAccInfo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.fitnik.authentication.domain.usecase.SetAccInfoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SetUpAccViewModel @Inject constructor(
    private val setAccInfoUseCases: SetAccInfoUseCases
): ViewModel() {

    private val _state = MutableStateFlow(SetUpAccState())
    val state: StateFlow<SetUpAccState> = _state

    fun onEvent(event: AccounEvent) {
        when (event) {
            is AccounEvent.CollectHeight -> {
                _state.value = _state.value.copy(
                    height = event.height
                )
            }
            is AccounEvent.CollectWeight -> {
                _state.value = _state.value.copy(
                    weight = event.weight
                )
            }
            is AccounEvent.CollectActivity -> {
                _state.value = _state.value.copy(
                    activity = event.activity
                )
            }
            is AccounEvent.CollectGender -> {
                _state.value = _state.value.copy(
                    gender = event.gender
                )
            }
            is AccounEvent.CollectObjective -> {
                _state.value = _state.value.copy(
                    objective = event.objective
                )
            }
            is AccounEvent.CollectBirthDate -> {
                _state.value = _state.value.copy(
                    birthDate = event.birthDate
                )
            }
            is AccounEvent.ToggleHeightUnit -> {
                _state.value = _state.value.copy(
                    isHeightInCm =  !state.value.isHeightInCm
                )
            }
            is AccounEvent.ToggleWeightUnit -> {
                _state.value = _state.value.copy(
                    isWeightInKg =  !state.value.isWeightInKg
                )
            }
            is AccounEvent.Confirm -> { confirm(event.context) }
        }
    }

    fun confirm(context: Context) {
        Log.d("State", "${state.value}")
        if (_state.value.gender.isNotBlank() &&
            _state.value.activity.isNotBlank() &&
            _state.value.objective.isNotBlank() &&
            _state.value.weight.isNotBlank() &&
            _state.value.height.isNotBlank()
        ) {
            // TODO
        } else {
            Toast.makeText(context, "You must fill all fields!", Toast.LENGTH_SHORT).show() // Quitar cuando terminemos
        }
    }
}