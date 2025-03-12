package com.example.fitnik.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.onboarding.model.OnboardingPagerModel
import com.example.fitnik.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingPagerModel(
            title = "Smart insights for your progress.",
            body = stringResource(R.string.ai_tracking_body),
            image = R.drawable.onb1,
            imageHeight = 470.dp
        ),
        OnboardingPagerModel(
            title = "Push your limits, achieve results.",
            body = stringResource(R.string.get_burn_body),
            image = R.drawable.onb2,
            imageHeight = 470.dp
        ),
        OnboardingPagerModel(
            title = "Healthy eating, made simple",
            body = stringResource(R.string.eat_well_body),
            image = R.drawable.onb3,
            imageHeight = 470.dp
        ),
        OnboardingPagerModel(
            title = "Rest better, live better.",
            body = stringResource(R.string.sleep_body),
            image = R.drawable.onb4,
            imageHeight = 470.dp
        )
    )

    OnboardingPager(pages = pages, onFinish = onFinish)
}