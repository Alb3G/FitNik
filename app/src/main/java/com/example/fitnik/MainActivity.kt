package com.example.fitnik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fitnik.navigation.NavigationHost
import com.example.fitnik.navigation.NavigationScreens
import com.example.fitnik.navigation.NavigationScreens.Home
import com.example.fitnik.navigation.NavigationScreens.Login
import com.example.fitnik.navigation.NavigationScreens.Onboarding
import com.example.fitnik.ui.theme.FitnikTheme
import com.example.fitnik.ui.theme.white
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.checkSessionState()
        setContent {
            FitnikTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = white
                ) {
                    if (viewModel.isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    } else {
                        val navController = rememberNavController()
                        NavigationHost(
                            navHostController = navController,
                            startDestination = getStartDestination()
                        )
                    }
                }
            }
        }
    }

    private fun getStartDestination(): NavigationScreens {
        return when {
            viewModel.isLoggedIn && viewModel.accIsCompleted -> Home
            viewModel.isLoggedIn -> Login
            viewModel.hasSeenOnboarding -> Login
            else -> Onboarding
        }
    }

}