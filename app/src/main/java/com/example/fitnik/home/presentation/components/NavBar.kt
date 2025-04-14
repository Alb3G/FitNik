package com.example.fitnik.home.presentation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.fitnik.home.presentation.model.BottomNavItem
import com.example.fitnik.ui.theme.white

@Composable
fun NavBar(
    navItems: List<BottomNavItem>,
    selectedItemIndex: Int,
    onNavItemClick: (Int, BottomNavItem) -> Unit
) {
    BottomAppBar(
        containerColor = white
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    onNavItemClick(index, item)
                },
                icon = {
                    if(index == selectedItemIndex)
                        Icon(painter = painterResource(item.selectedIcon), item.contentDescription)
                    else
                        Icon(painter = painterResource(item.unselectedIcon), item.contentDescription)
                }
            )
        }
    }
}