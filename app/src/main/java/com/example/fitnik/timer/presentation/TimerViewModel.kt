package com.example.fitnik.timer.presentation

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnik.timer.service.TimerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(TimerState())
    val state: StateFlow<TimerState> = _state.asStateFlow()

    @SuppressLint("StaticFieldLeak")
    private var timerService: TimerService? = null
    private var isBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TimerService.TimerBinder
            timerService = binder.getService()
            isBound = true

            // Start collecting state updates from service
            viewModelScope.launch {
                timerService?.timerState?.collectLatest { serviceState ->
                    _state.update { serviceState }
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            timerService = null
            isBound = false
        }
    }

    init {
        bindTimerService()
    }

    private fun bindTimerService() {
        Intent(context, TimerService::class.java).also { intent ->
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun toggleStartAndStop() {
        if (_state.value.isRunning) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    fun startTimer() {
        Intent(context, TimerService::class.java).also { intent ->
            intent.action = TimerService.ACTION_START
            ContextCompat.startForegroundService(context, intent)
        }
    }

    fun pauseTimer() {
        Intent(context, TimerService::class.java).also { intent ->
            intent.action = TimerService.ACTION_PAUSE
            ContextCompat.startForegroundService(context, intent)
        }
    }

    fun resetTimer() {
        Intent(context, TimerService::class.java).also { intent ->
            intent.action = TimerService.ACTION_RESET
            ContextCompat.startForegroundService(context, intent)
        }

        viewModelScope.launch {
            _state.value.times.clear()
        }
    }

    fun onLapClick() {
        Intent(context, TimerService::class.java).also { intent ->
            intent.action = TimerService.ACTION_LAP
            ContextCompat.startForegroundService(context, intent)
        }
    }

    fun biggestTime(time: Long): Boolean {
        return time == _state.value.times.maxOrNull()
    }

    fun lowestTime(time: Long): Boolean {
        return time == _state.value.times.minOrNull()
    }

    override fun onCleared() {
        if (isBound) {
            context.unbindService(serviceConnection)
            isBound = false
        }
        super.onCleared()
    }
}