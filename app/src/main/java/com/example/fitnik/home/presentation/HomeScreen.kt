package com.example.fitnik.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fitnik.home.navigation.MainTabsScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    mainNavController: NavHostController
) {
    val state by viewModel.state.collectAsState()

    MainTabsScreen(
        navItems = state.tabsItems,
        mainNavController = mainNavController,
        viewModel = viewModel
    )
}