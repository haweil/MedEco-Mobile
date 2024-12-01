package com.mhss.app.shifak.presentation.pharmacy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButtonedCard
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.pharmacy.branch.ManageBranchesCard
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun PharmacyHomeScreen(
    state: PharmacyHomeUiState,
    onNavigate: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PharmacyScreenAppBar(
            name = state.name,
            logo = state.logo,
            hasNotifications = state.hasNotifications,
            onNotificationsClick = {
                onNavigate(Screen.NotificationsScreen)
            }
        )
        ManageBranchesCard {
            onNavigate(Screen.ManageBranchesScreen(state.id))
        }
        Spacer(Modifier.height(12.dp))
        MainButtonedCard(
            text = stringResource(R.string.pharmacy_network_card),
            buttonText = stringResource(R.string.make_request),
            imagePainter = painterResource(R.drawable.ic_network)
        ) {
            onNavigate(Screen.RequestMedicineNetworkScreen)
        }
        Spacer(Modifier.height(12.dp))
        MainButtonedCard(
            text = stringResource(R.string.talk_to_our_assistant),
            buttonText = stringResource(R.string.chat_now),
            imagePainter = painterResource(R.drawable.ic_chatbot),
            onClick = {
                onNavigate(Screen.SmartAssistantScreen)
            }
        )
        Spacer(Modifier.height(12.dp))
        MainButtonedCard(
            text = stringResource(R.string.pharmacy_register_donation),
            buttonText = stringResource(R.string.register_donation),
            imagePainter = painterResource(R.drawable.dispose_expired_img),
        ) {
            onNavigate(Screen.ApproveDonationScreen)
        }

        Spacer(Modifier.height(18.dp).padding(WindowInsets.navigationBars.asPaddingValues()))
    }
}

@Composable
private fun PharmacyScreenAppBar(
    name: String,
    logo: String,
    hasNotifications: Boolean,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp)
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SubcomposeAsyncImage(
                model = logo,
                contentDescription = name,
                loading = { CircularProgressIndicator() },
                error = {
                    val randomColor = remember {
                        Color(
                            red = (0..200).random(),
                            green = (0..200).random(),
                            blue = (0..200).random()
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.ic_pharmacy),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(randomColor)
                            .padding(10.dp)
                            .size(22.dp),
                        tint = Color.White
                    )
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
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
private fun PharmacyHomePrevidw() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            PharmacyHomeScreen(
                state = PharmacyHomeUiState(
                    name = "Aldawaa",
                    hasNotifications = true,
                ),
                onNavigate = {}
            )
        }
    }
}