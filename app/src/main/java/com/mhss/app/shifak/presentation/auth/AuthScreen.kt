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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.ui.theme.DarkOnSurfaceColor
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun AuthScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.full_logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(96.dp))
        Text(
            text = stringResource(id = R.string.auth_screen_text),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            color = DarkOnSurfaceColor
        )
        Spacer(Modifier.height(64.dp))
        MainButton(
            modifier = Modifier.padding(horizontal = 42.dp),
            text = stringResource(R.string.login),
            onClick = {
                onLogin()
            })
        Spacer(Modifier.height(24.dp))
        MainButton(
            modifier = Modifier.padding(horizontal = 42.dp),
            text = stringResource(R.string.create_new_account),
            onClick = {
                onSignUp()
            })
        Spacer(Modifier.height(96.dp))
    }
}


@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun AuthScreenPreview() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AuthScreen(
                onLogin = { },
                onSignUp = { }
            )
        }
    }
}