package com.example.fitnik.core.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
@SuppressLint("ObsoleteSdkInt")
fun RequestNotificationPermission(onGranted: () -> Unit) {
  val context = LocalContext.current

  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission(),
    onResult = { granted ->
      if (granted) onGranted()
    }
  )

  // Necesitamos que el usuario acepte el permiso de poner mandarle notificaciones,
  // sino nuestro servicio no va a funcionar correctamente.
  LaunchedEffect(Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      when {
        ContextCompat.checkSelfPermission(
          context, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED -> {
          onGranted()
        }
        else -> {
          launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
      }
    } else {
      onGranted()
    }
  }

}
