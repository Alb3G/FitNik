package com.example.fitnik.timer.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.fitnik.R
import com.example.fitnik.timer.presentation.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.Default)

    private var timerJob: Job? = null

    private val _timerState = MutableStateFlow(TimerState())
    val timerState: StateFlow<TimerState> = _timerState.asStateFlow()

    private val binder = TimerBinder()

    inner class TimerBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onBind(intent: Intent?): IBinder? = binder

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTimer()
            ACTION_PAUSE -> pauseTimer()
            ACTION_RESET -> resetTimer()
            ACTION_STOP_SERVICE -> stopSelf()
        }
        return START_STICKY
    }

    private fun startTimer() {
        if (_timerState.value.isRunning) return

        _timerState.update {
            it.copy(
                isRunning = true,
                startButtonText = "Stop",
                resetLapButtonText = "Lap"
            )
        }

        startForeground(NOTIFICATION_ID, createNotification())

        timerJob?.cancel()
        timerJob = serviceScope.launch {
            val startTime = System.currentTimeMillis() - _timerState.value.time * 10
            while (true) {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
                _timerState.update {
                    it.copy(time = elapsedTime / 10) // Asi lo convertimos a centiseconds
                }
                updateNotification()
                delay(10) // Actualizamos cada 10ms
            }
        }
    }

    private fun pauseTimer() {
        timerJob?.cancel()

        _timerState.update {
            it.copy(
                isRunning = false,
                startButtonText = "Start",
                resetLapButtonText = "Reset"
            )
        }

        updateNotification()
    }

    private fun resetTimer() {
        if (_timerState.value.isRunning) return

        timerJob?.cancel()

        _timerState.update {
            it.copy(time = 0L)
        }

        updateNotification()
    }

    private fun createNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Timer")
        .setContentText(formatTime(_timerState.value.time))
        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setOngoing(true)
        .addAction(createStartPauseAction())
        .addAction(createResetAction())
        .build()

    private fun updateNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // Hay que añadir el permiso de post Notifications en el manifest.
        notificationManager.notify(NOTIFICATION_ID, createNotification())
    }

    private fun createStartPauseAction(): NotificationCompat.Action? {
        val actionText = if (_timerState.value.isRunning) "Stop" else "Start"

        val actionIntent = Intent(this, TimerService::class.java).apply {
            action = if (_timerState.value.isRunning) ACTION_PAUSE else ACTION_START
        }

        val pendingIntent = PendingIntent.getService(
            this, 1, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Action.Builder(
            R.drawable.play_svgrepo_com,
            actionText,
            pendingIntent
        ).build()
    }

    private fun createResetAction(): NotificationCompat.Action? {
        val actionText = if (_timerState.value.isRunning) "Lap" else "Reset"
        val actionIntent = Intent(this, TimerService::class.java).apply {
            action = ACTION_RESET
        }
        val pendingIntent = PendingIntent.getService(
            this,
            2,
            actionIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Action.Builder(
            R.drawable.reset_timer, // Add this icon to your drawable resources
            actionText,
            pendingIntent
        ).build()
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(time: Long): String {
        val centiSeconds = time % 100
        val seconds = (time / 100) % 60
        val minutes = (time / 6000) % 60

        return String.format("%02d:%02d:02d", minutes, seconds, centiSeconds)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        // Los notification channels se incluyeron en Android Oreo por eso debemos checkear la version.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                // ID para que las comunicaciones dentro de la app sean a traves del mismo canal
                CHANNEL_ID,
                "Timer Service Channel",
                // Hace que la notificacion se vea desde cualquier parte del dispositivo y hace sonido pero no es intrusiva
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Shows timer notification"
                setSound(null, null)
                enableVibration(false)
            }
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        timerJob?.cancel()
        serviceScope.cancel()
        super.onDestroy()
    }

    companion object {
        const val CHANNEL_ID = "timer_service_channel"
        const val NOTIFICATION_ID = 1

        const val ACTION_START = "com.example.fitnik.timer.action.START"
        const val ACTION_PAUSE = "com.example.fitnik.timer.action.PAUSE"
        const val ACTION_RESET = "com.example.fitnik.timer.action.RESET"
        const val ACTION_STOP_SERVICE = "com.example.fitnik.timer.action.STOP_SERVICE"
    }
}