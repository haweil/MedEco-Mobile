package com.mhss.app.shifak.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { onboardingItems.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) { i ->
            OnboardingCard(
                data = onboardingItems[i]
            )
        }

        Spacer(Modifier.height(64.dp))

        PagerIndicator(
            count = onboardingItems.size,
            currentPage = pagerState.currentPage
        )

        Spacer(Modifier.height(32.dp))


        MainButton(
            text = if (pagerState.currentPage == onboardingItems.lastIndex) {
                stringResource(id = R.string.start)
            } else {
                stringResource(id = R.string.continue_text)
            },
            onClick = {
                if (pagerState.currentPage == onboardingItems.lastIndex) {
                    onFinish()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        )
    }
}

@Composable
fun PagerIndicator(
    count: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { iteration ->
            val color =
                if (currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
    }
}

val onboardingItems = listOf(
    OnBoardingData(
        iconRes = R.drawable.onboarding_img1,
        titleRes = R.string.onboarding_title_1,
        descriptionRes = R.string.onboarding_description_1
    ),
    OnBoardingData(
        iconRes = R.drawable.onboarding_img2,
        titleRes = R.string.onboarding_title_2,
        descriptionRes = R.string.onboarding_description_2
    ),
    OnBoardingData(
        iconRes = R.drawable.onboarding_img3,
        titleRes = R.string.onboarding_title_3,
        descriptionRes = R.string.onboarding_description_3
    )
)

@Preview
@Composable
private fun OnBoardingScreenPreview() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            OnboardingScreen(
                onFinish = {}
            )
        }
    }
}