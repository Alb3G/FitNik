package com.example.fitnik.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.R
import com.example.fitnik.home.presentation.components.NavBar
import com.example.fitnik.ui.theme.smokeWhite

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onBottomNavItemClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = {
            NavBar(navItems = state.tabsItems, state.selectedItemIndex) { index, item ->
                onBottomNavItemClick(item.title)
                state.selectedItemIndex = index
            }
        }
    ) { paddingValues ->
        if (state.workouts.isEmpty()) {
            Column(Modifier
                .background(smokeWhite)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.appicon),
                    "App Icon",
                    modifier = Modifier.alpha(0.3f)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text("No Workouts Available", style = MaterialTheme.typography.headlineSmall)
            }
        } else {
            Column(Modifier
                .background(smokeWhite)
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
            ) {

            }
        }
    }
}