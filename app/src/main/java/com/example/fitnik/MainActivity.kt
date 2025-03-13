package com.example.fitnik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fitnik.navigation.NavigationHost
import com.example.fitnik.navigation.NavigationScreens.Onboarding
import com.example.fitnik.ui.theme.FitnikTheme
import com.example.fitnik.ui.theme.white
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnikTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = white
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = Onboarding
                    )
                }
            }
        }
    }
}