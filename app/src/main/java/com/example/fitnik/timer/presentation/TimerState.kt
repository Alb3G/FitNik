package com.example.fitnik.timer.presentation

data class TimerState(
    val time: String = "00:00:00",
    val startButtonText: String = "Start",
    val resetLapButtonText: String = "Lap",
    val isRunning: Boolean = false
)
