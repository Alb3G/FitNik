package com.example.fitnik.home.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.home.domain.usecase.GetRoutinesUseCase
import com.example.fitnik.routineDetail.domain.RoutineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRoutinesUseCase: GetRoutinesUseCase,
    private val routineRepository: RoutineRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadRoutines()
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            // Primero establecemos el estado de carga
            _state.update { it.copy(isLoading = true) }

            // Luego recogemos los datos
            getRoutinesUseCase().collectLatest { routines ->
                // Actualizamos el estado con los datos y desactivamos la carga en una sola operación
                _state.update { currentState ->
                    currentState.copy(
                        routines = routines,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateSelectedIndex(index: Int) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(selectedItemIndex = index)
            }
        }
    }

    fun toggleLoading() {
        _state.update {
            it.copy(isLoading = !it.isLoading)
        }
    }

    fun deleteRoutine(
        routineId: String,
        context: Context
    ) {
        viewModelScope.launch {
            toggleLoading()
            routineRepository.deleteRoutineById(routineId)
                .onSuccess { Toast.makeText(context, "Routine deleted succesfully", Toast.LENGTH_SHORT).show() }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.message ?: "Error al eliminar la rutina",
                            isLoading = false
                        )
                    }

                    Toast.makeText(context, _state.value.errorMessage, Toast.LENGTH_SHORT).show()
                }
        }
    }
}