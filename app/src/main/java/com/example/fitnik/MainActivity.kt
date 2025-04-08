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
import com.example.fitnik.navigation.NavigationHost
import com.example.fitnik.navigation.NavigationScreens
import com.example.fitnik.navigation.NavigationScreens.Home
import com.example.fitnik.navigation.NavigationScreens.Login
import com.example.fitnik.navigation.NavigationScreens.Onboarding
import com.example.fitnik.navigation.NavigationScreens.UserProfileSetUp
import com.example.fitnik.ui.theme.FitnikTheme
import com.example.fitnik.ui.theme.white
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseFirestore.setLoggingEnabled(true)
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
                        is NavigationState.Loading -> {  }
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

    fun NavigationState.toNavigationScreen(): NavigationScreens {
        return when (this) {
            NavigationState.NeedsOnboarding -> Onboarding
            NavigationState.NotLoggedIn -> Login
            NavigationState.LoggedInIncomplete -> UserProfileSetUp
            NavigationState.LoggedInComplete -> Home
            NavigationState.Loading -> Login
        }
    }

}