package com.example.fitnik.wod.presentation

import androidx.lifecycle.ViewModel
import com.example.fitnik.core.domain.model.Wod
import com.example.fitnik.core.domain.model.WodHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WodViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(WodState())
    val state: StateFlow<WodState> = _state.asStateFlow()

    init {
        val history = List(7) { index ->
            val date = _state.value.historyStartDate.plusDays(index.toLong())
            WodHistory(
                date = date,
                completed = index % 3 != 0
            )
        }

        val sampleWod = Wod(
            name = "Burpees Challenge",
            description = "10 burpees a máximo ritmo en cada ronda. Descansa 30 segundos entre rondas.",
            rounds = 3,
            durationMinutes = 5
        )

        _state.update {
            it.copy(
                currentWod = sampleWod,
                streak = 5,
                history = history,
                remainingSeconds = 180
            )
        }
    }

    fun onEvent(event: WodEvent) {
        when (event) {
            is WodEvent.CompleteWod -> {  }
            is WodEvent.StartWod -> {  }
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
                completed = index % 3 != 0
            )
        }
    }
}