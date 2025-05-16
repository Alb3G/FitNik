package com.example.fitnik.home.presentation

import com.example.fitnik.R
import com.example.fitnik.core.domain.model.Routine
import com.example.fitnik.home.presentation.model.BottomNavItem

data class HomeState(
    val routines: List<Routine> = emptyList(),
    val tabsItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = R.drawable.home_selected,
            unselectedIcon = R.drawable.home,
            contentDescription = "Home Screen Icon"
        ),
        BottomNavItem(
            title = "Wods",
            selectedIcon = R.drawable.dumbell_icon,
            unselectedIcon = R.drawable.dumbell_icon_unselected,
            contentDescription = "Steps Screen Icon"
        ),
        BottomNavItem(
            title = "Timer",
            selectedIcon = R.drawable.duration_selected,
            unselectedIcon = R.drawable.duration,
            contentDescription = "Timer Screen Icon"
        ),
        BottomNavItem(
            title = "Settings",
            selectedIcon = R.drawable.settings_selected,
            unselectedIcon = R.drawable.settings,
            contentDescription = "Settings Screen Description"
        )
    ),
    var selectedItemIndex: Int = 0,
    var isLoading: Boolean = false,
    val errorMessage: String = ""
)
