package com.example.fitnik.home.presentation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.fitnik.home.presentation.model.BottomNavItem
import com.example.fitnik.ui.theme.midGray
import com.example.fitnik.ui.theme.primary
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
            val iconTint = if (index == selectedItemIndex) {
                primary
            } else {
                midGray
            }
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    onNavItemClick(index, item)
                },
                icon = {
                    if(index == selectedItemIndex)
                        Icon(
                            painter = painterResource(item.selectedIcon),
                            contentDescription = item.contentDescription,
                            tint = iconTint
                        )
                    else
                        Icon(
                            painter = painterResource(item.unselectedIcon),
                            contentDescription = item.contentDescription,
                            tint = iconTint
                        )
                }
            )
        }
    }
}