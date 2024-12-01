package com.mhss.app.shifak.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.util.UserType

@Composable
fun AccountTypeScreen(
    onNavigate: (UserType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.full_logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(128.dp))
        MainButton(
            text = stringResource(R.string.normal_user),
            onClick = {
                onNavigate(UserType.USER)
            },
            modifier = Modifier.padding(horizontal = 42.dp),

            )
        Spacer(Modifier.height(24.dp))
        MainButton(
            text = stringResource(R.string.pharmacy),
            onClick = {
                onNavigate(UserType.PHARMACY)
            },
            modifier = Modifier.padding(horizontal = 42.dp),
        )
        Spacer(Modifier.height(96.dp))
    }
}


@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun AccountTypeScreenPreview() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AccountTypeScreen(onNavigate = {})
        }
    }
}