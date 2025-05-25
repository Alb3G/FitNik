package com.example.fitnik.settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.R
import com.example.fitnik.core.domain.mappers.toEntity
import com.example.fitnik.settings.presentation.components.MenuItemRow
import com.example.fitnik.settings.presentation.components.StatItem
import com.example.fitnik.ui.theme.lightLightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.white

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.displayLarge
                )
            }

            // Profile Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(lightLightGray)
                ) {
                    // Replace with actual profile image
                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.appicon),
                        contentDescription = "Profile Picture",
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Name
                Text(
                    text = state.user?.toEntity()?.firstName ?: "Stefani Wong",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Program
                Text(
                    text = state.user?.toEntity()?.objective ?: "Lose a Fat Program",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            // Stats Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Height
                StatItem(
                    value = state.user?.toEntity()?.height?.toString() ?: "180 cm",
                    label = "Height"
                )

                // Weight
                StatItem(
                    value = state.user?.toEntity()?.weight?.toString() ?: "65 kg",
                    label = "Weight"
                )

                // Age
                StatItem(
                    value = state.user?.toEntity()?.age?.toString() ?: "22 yo",
                    label = "Age"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Account Section
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Account",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    // Personal Data
                    MenuItemRow(
                        icon = R.drawable.profile,
                        title = "Personal Data",
                        iconTint = Color(0xFF8CB3F5)
                    ) { /* Personal Data navigation */ }

                    // Achievement
                    MenuItemRow(
                        icon = R.drawable.document,
                        title = "Achievement",
                        iconTint = Color(0xFF8CB3F5)
                    ) { /* Achivement navigation */ }

                    // Activity History
                    MenuItemRow(
                        icon = R.drawable.graph,
                        title = "Activity History",
                        iconTint = Color(0xFF8CB3F5)
                    ) { /* Activity navigation */ }

                    // Workout Progress
                    MenuItemRow(
                        icon = R.drawable.chart,
                        title = "Workout Progress",
                        iconTint = Color(0xFF8CB3F5)
                    ) { /* Workout progress navigation */ }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Notification Section
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Notification",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.notification),
                            contentDescription = "Notifications",
                            tint = Color(0xFF8CB3F5),
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Pop-up Notification",
                            fontSize = 16.sp
                        )
                    }

                    Switch(
                        modifier = Modifier.size(width = 36.dp, height = 18.dp),
                        checked = true,
                        onCheckedChange = { /* TODO: Toggle notification */ },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = white,
                            checkedTrackColor = secondary,
                            uncheckedThumbColor = white,
                            uncheckedTrackColor = lightLightGray
                        )
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primary
                ),
                onClick = {
                    viewModel.onLogOut()
                    onLogout()
                }
            ) {
                Text("Log Out", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}