package com.example.fitnik.onboarding.presentation.components

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.onboarding.model.OnboardingPagerModel
import com.example.fitnik.ui.theme.midGray
import com.example.fitnik.ui.theme.primary
import kotlinx.coroutines.launch

@Composable
fun OnboardingPager(
    pages: List<OnboardingPagerModel>,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    Box(modifier = modifier) {
        HorizontalPager(state = pagerState) { index ->
            val information = pages[index]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
            ) {
                Image(
                    painter = painterResource(information.image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(470.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(information.title, style = MaterialTheme.typography.displaySmall)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = information.body, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (pagerState.currentPage == pagerState.pageCount - 1) {
                FitnikDefButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    onAction = { onFinish() }
                ) { Text("Get Started", style = MaterialTheme.typography.titleMedium) }
            } else {
                TextButton(onClick = onFinish) {
                    Text("Skip",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = Bold
                        )
                    )
                }
                HorizontalPagerIndicator(pagerState.currentPage, pagerState.pageCount)
                TextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text("Next",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalPagerIndicator(currentPage: Int, pages: Int) {
    Row(
        modifier = Modifier.width(100.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pages) { index ->
            val alpha by animateFloatAsState(targetValue = if (currentPage == index) 1f else 0.5f, label = "")

            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(12.dp)
                    .background(if (currentPage == index) primary else midGray, shape = CircleShape)
                    .alpha(alpha)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnboardingPager() {
    val samplePages = listOf(
        OnboardingPagerModel(
            title = "AI Tracking Goals",
            body = "",
            image = R.drawable.onb1,
        ),
        OnboardingPagerModel(
            title = "AI Tracking Goals",
            body = "",
            image = R.drawable.onb2,
        ),
        OnboardingPagerModel(
            title = "AI Tracking Goals",
            body = "",
            image = R.drawable.onb3,
        ),
        OnboardingPagerModel(
            title = "AI Tracking Goals",
            body = "",
            image = R.drawable.onb4,
        )
    )

    OnboardingPager(
        pages = samplePages,
        onFinish = {},
        modifier = Modifier.fillMaxSize()
    )
}