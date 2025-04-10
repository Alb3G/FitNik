package com.example.fitnik.authentication.presentation.setUpAccInfo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.SetAccInfoUseCases
import com.example.fitnik.core.domain.model.Workout
import com.example.fitnik.core.domain.model.TrainingMockProvider
import com.example.fitnik.core.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpAccViewModel @Inject constructor(
    private val authRepository: AuthRepository,
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
        loading()
        viewModelScope.launch {
            if (_state.value.gender.isNotBlank() &&
                _state.value.activity.isNotBlank() &&
                _state.value.objective.isNotBlank() &&
                _state.value.weight.isNotBlank() &&
                _state.value.height.isNotBlank()
            ) {
                val uid = authRepository.getUserId()
                uid?.let {
                    val user = authRepository.getUserObjFromFirestore(it).getOrNull()
                    val trainings = loadTrainingBasedInObjective(_state.value.objective, user)
                    val fields = mapOf(
                        "email" to user?.email,
                        "firstName" to user?.firstName,
                        "gender" to _state.value.gender,
                        "activityLvl" to _state.value.activity,
                        "objective" to _state.value.objective,
                        "age" to setAccInfoUseCases.getUserAgeUseCase(_state.value.birthDate),
                        "weight" to setAccInfoUseCases.convertWeightUseCase(_state.value.weight.toDouble(), _state.value.isWeightInKg),
                        "height" to setAccInfoUseCases.convertHeightUseCase(_state.value.height.toDouble(), _state.value.isHeightInCm),
                        "workouts" to trainings,
                        "accIscomplete" to true
                    )
                    setAccInfoUseCases.updateUserFromFirestoreUseCase(it, fields).onFailure {
                        val message = it.message
                        Log.d("Update User Failed", message.toString())
                    }
                    _state.value = _state.value.copy(
                        accIsComplete = true,
                        isLoading = false
                    )
                }
            } else {
                Toast.makeText(context, "You must fill all fields!", Toast.LENGTH_SHORT).show() // Quitar cuando terminemos
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun loading() {
        _state.value = _state.value.copy(isLoading = true)
    }

    private fun loadTrainingBasedInObjective(objective: String, user: User?): List<Workout> {
        val currentTrainings = user?.workouts?.toMutableList() ?: mutableListOf()

        when (objective) {
            "Gain Muscle" -> currentTrainings.add(TrainingMockProvider.getMuscleGainTraining())
            "Loose Fat" -> currentTrainings.add(TrainingMockProvider.getFatLossTraining())
            "Loose weight" -> currentTrainings.add(TrainingMockProvider.getWeightLossTraining())
        }

        return currentTrainings
    }
}