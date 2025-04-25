package com.example.fitnik.routineDetail

import androidx.lifecycle.ViewModel
import com.example.fitnik.home.domain.usecase.GetRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RoutineDetailViewModel @Inject constructor(
    private val getRoutineUseCase: GetRoutineUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RoutineState())
    val state: StateFlow<RoutineState> = _state.asStateFlow()


}