package com.example.fitnik.authentication.presentation.setUpAccInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.setUpAccInfo.components.InputsGroup
import com.example.fitnik.ui.theme.white

@Composable
fun UserProfileSetUpScreen(
    viewModel: SetUpAccViewModel = hiltViewModel(),
    navigateHome: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val titleTextStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)

    LaunchedEffect(key1 = state.accIsComplete) {
        if (state.accIsComplete)
            navigateHome()
    }

    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.set_up_acc_img),"Image of a woman using dumbells")

        Text(
            "Let’s complete your profile",
            style = titleTextStyle,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            "It will help us to know more about you!",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputsGroup(viewModel = viewModel, state = state)
    }
}