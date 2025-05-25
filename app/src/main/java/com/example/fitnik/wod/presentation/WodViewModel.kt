package com.example.fitnik.wod.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.core.domain.model.Wod
import com.example.fitnik.core.domain.model.WodHistory
import com.example.fitnik.wod.domain.remote.WodChallengesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WodViewModel @Inject constructor(
    private val wodChallengesApi: WodChallengesApi
) : ViewModel() {

    private val _state = MutableStateFlow(WodState())
    val state: StateFlow<WodState> = _state.asStateFlow()

    private val completedDates = mutableSetOf<LocalDate>()

    init {
        _state.update {
            it.copy(
                streak = 0,
                history = generateHistoryForDateRange(LocalDate.now()),
                remainingSeconds = 180
            )
        }

        val wod = fetchWod()
    }

    fun onEvent(event: WodEvent) {
        when (event) {
            is WodEvent.CompleteWod -> {
                completeWod()
                updateHistory()
                updateStreak()
            }
            is WodEvent.NextPeriod -> updateNextPeriod()
            is WodEvent.PreviousPeriod -> updatePreviousPeriod()
        }
    }

    private fun updateNextPeriod() {
        _state.update { currentState ->
            val newStartDate = currentState.historyStartDate.plusDays(7)
            val newHistory = generateHistoryForDateRange(newStartDate)
            currentState.copy(
                historyStartDate = newStartDate,
                history = newHistory
            )
        }
    }

    private fun updatePreviousPeriod() {
        _state.update { currentState ->
            val newStartDate = currentState.historyStartDate.minusDays(7)
            val newHistory = generateHistoryForDateRange(newStartDate)
            currentState.copy(
                historyStartDate = newStartDate,
                history = newHistory
            )
        }
    }

    private fun generateHistoryForDateRange(startDate: LocalDate): List<WodHistory> {
        return List(7) { index ->
            val date = startDate.plusDays(index.toLong())
            WodHistory(
                date = date,
                completed = completedDates.contains(date)
            )
        }
    }

    private fun completeWod() {
        _state.update {
            it.copy(isCompleted = true)
        }
    }

    private fun updateStreak() {
        _state.update { it.copy(streak = _state.value.streak + 1) }
    }

    private fun updateHistory() {
        val today = LocalDate.now()
        completedDates.add(today)

        _state.update { currentState ->
            val startDate = currentState.historyStartDate
            val endDate = startDate.plusDays(6)

            // Verificar si el dia actual está dentro del rango visible
            if (today.isBefore(startDate) || today.isAfter(endDate)) {
                return@update currentState
            }

            // Calcular el índice correspondiente al día actual
            val dayIndex = (today.toEpochDay() - startDate.toEpochDay()).toInt()

            // Crear una nueva lista con el día actual marcado como completado
            val updatedHistory = currentState.history.toMutableList()
            updatedHistory[dayIndex] = updatedHistory[dayIndex].copy(completed = true)

            currentState.copy(history = updatedHistory)
        }
    }

    private fun fetchWod() {
        viewModelScope.launch {
            try {
                val wodResponse = wodChallengesApi.getWod()
                Log.i("Wod Response", "$wodResponse")

                // Update state with the API response
                _state.update { currentState ->
                    currentState.copy(
                        currentWod = Wod(
                            name = wodResponse.name,
                            description = wodResponse.description,
                            rounds = wodResponse.rounds,
                            durationMinutes = wodResponse.durationMin,
                            isCompleted = false
                        )
                    )
                }
            } catch (e: Exception) {
                Log.e("WodViewModel", "Error fetching WOD", e)
                // You could add an error state field if needed
                _state.update { currentState ->
                    currentState.copy(
                        currentWod = Wod(
                            name = "Error",
                            description = "Failed to load workout: ${e.message}",
                            rounds = 0,
                            durationMinutes = 0,
                            isCompleted = false
                        )
                    )
                }
            }
        }
    }
}