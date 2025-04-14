package com.example.fitnik.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnik.authentication.presentation.login.LoginScreen
import com.example.fitnik.authentication.presentation.setUpAccInfo.UserProfileSetUpScreen
import com.example.fitnik.authentication.presentation.signup.SignUpScreen
import com.example.fitnik.home.presentation.HomeScreen
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.Login
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.Onboarding
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.SignUp
import com.example.fitnik.navigation.NavigationGraph.AuthGraph.UserProfileSetUp
import com.example.fitnik.navigation.NavigationGraph.MainGraph
import com.example.fitnik.navigation.NavigationGraph.MainGraph.Home
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.Settings
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.StepsScreen
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.Timer
import com.example.fitnik.onboarding.presentation.OnboardingScreen
import com.example.fitnik.settings.SettingsScreen
import com.example.fitnik.steps.StepsScreen
import com.example.fitnik.timer.TimerScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationGraph
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

        composable<SignUp>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            SignUpScreen(
                onLoginClick = {
                    navHostController.popBackStack()
                    navHostController.navigate(Login)
                },
                onSignedUpSuccess = { navHostController.navigate(UserProfileSetUp) }
            )
        }

        composable<Login>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            LoginScreen(
                onRegisterClick = {
                    navHostController.navigate(SignUp)
                },
                onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(Home)
                },
                onAccCompleted = {
                    navHostController.popBackStack()
                    navHostController.navigate(UserProfileSetUp)
                }
            )
        }

        composable<Home>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            HomeScreen(
                onBottomNavItemClick = { title ->
                    when (title) {
                        "Home" -> tabsNav(navHostController, Home)
                        "Steps" -> tabsNav(navHostController,StepsScreen)
                        "Timer" -> tabsNav(navHostController,Timer)
                        "Settings" -> tabsNav(navHostController,Settings)
                    }
                }
            )
        }

        composable<UserProfileSetUp>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            UserProfileSetUpScreen {
                navHostController.popBackStack()
                navHostController.navigate(Home)
            }
        }

        composable<StepsScreen>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            StepsScreen()
        }

        composable<Timer>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            TimerScreen()
        }

        composable<Settings>(
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            SettingsScreen()
        }
    }
}

private fun tabsNav(navHostController: NavHostController, destination: MainGraph) {
    navHostController.navigate(destination) {
        popUpTo(navHostController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}