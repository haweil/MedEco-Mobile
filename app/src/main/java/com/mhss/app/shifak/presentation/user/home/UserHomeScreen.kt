package com.mhss.app.shifak.presentation.user.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButtonedCard
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.presentation.user.home.components.HomeDonateCard
import com.mhss.app.shifak.presentation.user.home.components.HomeExpiredCard
import com.mhss.app.shifak.presentation.user.home.components.HomePointsCard

@Composable
fun UserHomeScreen(
    state: UserHomeUiState,
    onNavigate: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreenAppBar(
            name = state.userName,
            hasNotifications = state.hasNotifications,
            onNotificationsClick = {
                // TODO
            }
        )
        Spacer(Modifier.height(18.dp))
        HomePointsCard(
            points = state.points,
        ) {

        }
        Spacer(Modifier.height(18.dp))
        MainButtonedCard(
            text = stringResource(R.string.talk_to_our_assistant),
            buttonText = stringResource(R.string.chat_now),
            imagePainter = painterResource(R.drawable.ic_chatbot),
            onClick = {
                onNavigate(Screen.SmartAssistantScreen)
            }
        )
        Spacer(Modifier.height(18.dp))
        MainButtonedCard(
            text = stringResource(R.string.donate_home_card),
            buttonText = stringResource(R.string.donate_now),
            imagePainter = painterResource(R.drawable.donate_home_card_img)
        ) {
            onNavigate(
                Screen.NearbyPharmaciesScreen
            )
        }
        Spacer(Modifier.height(18.dp))
        MainButtonedCard(
            text = stringResource(R.string.expired_home_card),
            buttonText = stringResource(R.string.dispose_now),
            imagePainter = painterResource(R.drawable.dispose_expired_img)
        ) {
            onNavigate(
                Screen.NearbyPharmaciesScreen
            )
        }
        Spacer(Modifier.height(48.dp))
    }
}

@Composable
private fun HomeScreenAppBar(
    name: String,
    hasNotifications: Boolean,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.welcome) + ", ",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text =name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
        BadgedBox(
            badge = {
                if (hasNotifications) Badge()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_notifications),
                contentDescription = stringResource(R.string.notifications),
                modifier = Modifier.clickable { onNotificationsClick() }
            )
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun LoginScreenPreview() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            UserHomeScreen(
                state = UserHomeUiState(
                    userName = "Mohamed",
                    hasNotifications = true,
                    points = 1234
                ),
                onNavigate = {},
            )
        }
    }
}