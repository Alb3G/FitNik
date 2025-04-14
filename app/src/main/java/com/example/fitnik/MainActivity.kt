package com.example.fitnik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fitnik.navigation.NavigationGraph
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.Login
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.Onboarding
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.UserProfileSetUp
import com.example.fitnik.navigation.NavigationGraph.MainGraph.Home
import com.example.fitnik.navigation.NavigationHost
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
                    val navController = rememberNavController()
                    val navigationState by viewModel.navigationState.collectAsState()

                    when (navigationState) {
                        is NavigationState.Loading -> {
                            /* Idear algo para un tiempo de carga excesivo */
                        }
                        else -> {
                            NavigationHost(
                                navHostController = navController,
                                startDestination = navigationState.toNavigationScreen()
                            )
                        }
                    }
                }
            }
        }
    }

    fun NavigationState.toNavigationScreen(): NavigationGraph {
        return when (this) {
            NavigationState.NeedsOnboarding -> Onboarding
            NavigationState.NotLoggedIn -> Login
            NavigationState.LoggedInIncomplete -> UserProfileSetUp
            NavigationState.LoggedInComplete -> Home
            NavigationState.Loading -> Login
        }
    }

}