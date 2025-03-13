package com.example.fitnik.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnik.navigation.NavigationScreens.Login
import com.example.fitnik.navigation.NavigationScreens.Onboarding
import com.example.fitnik.onboarding.domain.usecase.HasSeenOnboardingUseCase
import com.example.fitnik.onboarding.presentation.OnboardingScreen
import com.example.fitnik.onboarding.presentation.OnboardingViewModel

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationScreens
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<Onboarding> {
            OnboardingScreen(
                onFinish = { navHostController.navigate(Login) }
            )
        }

        composable<Login> {
            Text("Login")
        }
    }
}