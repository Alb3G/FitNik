package com.example.fitnik.authentication.presentation.setUpAccInfo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.authentication.domain.repository.AuthRepository
import com.example.fitnik.authentication.domain.usecase.SetAccInfoUseCases
import com.example.fitnik.core.data.preferences.UserPreferencesManager
import com.example.fitnik.core.domain.model.TrainingMockProvider
import com.example.fitnik.core.domain.model.User
import com.example.fitnik.core.domain.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpAccViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val setAccInfoUseCases: SetAccInfoUseCases,
    private val userPreferencesManager: UserPreferencesManager,
): ViewModel() {

    private val _state = MutableStateFlow(SetUpAccState())
    val state: StateFlow<SetUpAccState> = _state.asStateFlow()

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
                val uid = userPreferencesManager.getUserId().first() ?: authRepository.getUserId()
                uid?.let { userId ->
                    val user = setAccInfoUseCases.getRoomUserUseCase(userId) ?: authRepository.getUserObjFromFirestore(userId).getOrNull()
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
                    val modifiedUser = user?.copy(
                        uid = user.uid,
                        gender = _state.value.gender,
                        activityLvl = _state.value.activity,
                        objective = _state.value.objective,
                        age = setAccInfoUseCases.getUserAgeUseCase(_state.value.birthDate),
                        weight = setAccInfoUseCases.convertWeightUseCase(_state.value.weight.toDouble(), _state.value.isWeightInKg),
                        height = setAccInfoUseCases.convertHeightUseCase(_state.value.height.toDouble(), _state.value.isHeightInCm),
                        workouts = trainings,
                        accIscomplete = true
                    )
                    if (!setAccInfoUseCases.updateRoomUserUseCase(modifiedUser!!)) {
                        Log.d("UpdateUser", "Room update failed, trying Firestore")
                        setAccInfoUseCases.updateUserFromFirestoreUseCase(userId, fields).onFailure {
                            val message = it.message
                            Log.d("Update User in Firestore Failed", message.toString())
                        }
                    }
                    _state.value = _state.value.copy(
                        accIsComplete = true,
                        isLoading = false
                    )
                }
            } else {
                Toast.makeText(context, "All fields must be filled!", Toast.LENGTH_SHORT).show() // Quitar cuando terminemos
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