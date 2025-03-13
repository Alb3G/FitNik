package com.example.fitnik.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnik.R
import com.example.fitnik.onboarding.model.OnboardingPagerModel
import com.example.fitnik.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit
) {
    /**
     * Evitamos Mostrar el onboarding de nuevo si el usuario ya lo ha visto
     * y algun componente llama de nuevo al onboarding
     */
    LaunchedEffect(key1 = viewModel.hasSeenOnboarding) {
        if (viewModel.hasSeenOnboarding) {
            onFinish()
        }
    }

    val pages = listOf(
        OnboardingPagerModel(
            title = "Smart insights for your progress.",
            body = stringResource(R.string.ai_tracking_body),
            image = R.drawable.onb1,
        ),
        OnboardingPagerModel(
            title = "Push your limits, achieve results.",
            body = stringResource(R.string.get_burn_body),
            image = R.drawable.onb2,
        ),
        OnboardingPagerModel(
            title = "Healthy eating, made simple",
            body = stringResource(R.string.eat_well_body),
            image = R.drawable.onb3,
        ),
        OnboardingPagerModel(
            title = "Rest better, live better.",
            body = stringResource(R.string.sleep_body),
            image = R.drawable.onb4,
        )
    )

    OnboardingPager(pages = pages, onFinish = onFinish)
}