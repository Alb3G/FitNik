package com.example.fitnik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnik.authentication.presentation.login.LoginScreen
import com.example.fitnik.navigation.NavigationScreens.Login
import com.example.fitnik.navigation.NavigationScreens.Onboarding
import com.example.fitnik.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationScreens
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<Onboarding> {
            OnboardingScreen(
                onFinish = {
                    navHostController.navigate(Login)
                }
            )
        }

        composable<Login> {
            LoginScreen()
        }
    }
}