package com.example.fitnik.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnik.authentication.presentation.login.LoginScreen
import com.example.fitnik.authentication.presentation.setUpAccInfo.UserProfileSetUpScreen
import com.example.fitnik.authentication.presentation.signup.SignUpScreen
import com.example.fitnik.navigation.NavigationScreens.Home
import com.example.fitnik.navigation.NavigationScreens.Login
import com.example.fitnik.navigation.NavigationScreens.Onboarding
import com.example.fitnik.navigation.NavigationScreens.SignUp
import com.example.fitnik.navigation.NavigationScreens.UserProfileSetUp
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
                    navHostController.popBackStack()
                    navHostController.navigate(Login)
                }
            )
        }

        composable<SignUp> {
            SignUpScreen(
                onLoginClick = {
                    navHostController.popBackStack()
                    navHostController.navigate(Login)
                },
                onSignedUpSuccess = { navHostController.navigate(Home) }
            )
        }

        composable<Login> {
            LoginScreen(
                onRegisterClick = {
                    navHostController.navigate(SignUp)
                },
                onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(Home)
                },
                onGoogleLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(UserProfileSetUp)
                }
            )
        }

        composable<Home> {
            Text("Home")
        }

        composable<UserProfileSetUp> {
            UserProfileSetUpScreen {
                navHostController.popBackStack()
                navHostController.navigate(Home)
            }
        }
    }
}