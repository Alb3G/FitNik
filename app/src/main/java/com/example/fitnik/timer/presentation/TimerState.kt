package com.example.fitnik.timer.presentation

data class TimerState(
    var time: Long = 0L,
    val startButtonText: String = "Start",
    val resetLapButtonText: String = "Lap",
    val isRunning: Boolean = false
)
