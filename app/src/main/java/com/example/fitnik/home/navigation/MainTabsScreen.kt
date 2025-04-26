package com.example.fitnik.home.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fitnik.home.presentation.HomContent
import com.example.fitnik.home.presentation.HomeViewModel
import com.example.fitnik.home.presentation.components.NavBar
import com.example.fitnik.home.presentation.model.BottomNavItem
import com.example.fitnik.navigation.NavigationGraph.MainGraph.CreateWorkout
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.Home
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.RoutineDetail
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.Settings
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.StepsScreen
import com.example.fitnik.navigation.NavigationGraph.MainGraph.HomeTabsGraph.Timer
import com.example.fitnik.navigation.tabsNav
import com.example.fitnik.routineDetail.presentation.RoutineDetailScreen
import com.example.fitnik.settings.SettingsScreen
import com.example.fitnik.steps.StepsScreen
import com.example.fitnik.timer.TimerScreen

@Composable
fun MainTabsScreen(
    navItems: List<BottomNavItem>,
    mainNavController: NavHostController,
    viewModel: HomeViewModel
) {
    val tabsNavController = rememberNavController()
    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = {
            NavBar(
                navItems = navItems,
                selectedItemIndex = state.selectedItemIndex
            ) { index, item ->
                viewModel.updateSelectedIndex(index)
                when (item.title) {
                    "Home" -> tabsNav(tabsNavController, Home)
                    "Steps" -> tabsNav(tabsNavController, StepsScreen)
                    "Timer" -> tabsNav(tabsNavController, Timer)
                    "Settings" -> tabsNav(tabsNavController, Settings)
                }
            }
        }
    ) { paddingValues ->
        // NavHost anidado para las pestañas usando navegación con tipos seguros
        NavHost(
            navController = tabsNavController,
            startDestination = Home,
            modifier = Modifier.padding(paddingValues)
        ) {
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
                HomContent(
                    onCreateRoutineClick = { mainNavController.navigate(CreateWorkout) },
                    onRoutineClick = { tabsNavController.navigate(RoutineDetail(it)) }
                )
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

            composable<RoutineDetail>(
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
            ) { backStackEntry ->
                val detail = backStackEntry.toRoute<RoutineDetail>()
                RoutineDetailScreen(routineId = detail.id)
            }
        }
    }
}