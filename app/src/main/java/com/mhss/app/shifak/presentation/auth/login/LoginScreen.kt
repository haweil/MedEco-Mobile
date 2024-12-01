package com.mhss.app.shifak.presentation.auth.login

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.ui.theme.PrimaryColor
import com.mhss.app.shifak.presentation.ui.theme.SecondaryColor
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.util.UserType


@Composable
fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginScreenEvent) -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(state.done, state.error) {
        if (state.done) {
            if (state.userType == UserType.USER) {
                onEvent(LoginScreenEvent.Navigate(Screen.UserMainScreen, true))
            } else {
                onEvent(LoginScreenEvent.Navigate(Screen.PharmacyMainScreen, true))
            }
        }
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.login),
                onNavigateUp = { onEvent(LoginScreenEvent.NavigateUp) },
                contentColor = Color.White
            )
        },
        modifier = Modifier.background(
            Brush.verticalGradient(
                listOf(PrimaryColor, SecondaryColor)
            )
        ),
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.login_to_continue),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(Modifier.height(38.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 48.dp, topStart = 48.dp))
                        .background(Color.White)
                        .padding(start = 32.dp, end = 32.dp, top = 43.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    MainTextField(
                        value = email,
                        onValueChange = { email = it },
                        hint = stringResource(id = R.string.email),
                        keyboardType = KeyboardType.Email
                    )
                    Spacer(Modifier.height(24.dp))
                    MainTextField(
                        value = password,
                        onValueChange = { password = it },
                        hint = stringResource(id = R.string.password),
                        keyboardType = KeyboardType.Password,
                        hiddenText = isPasswordHidden,
                        trailingIcon = {
                            IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                                Icon(
                                    painter = if (isPasswordHidden) painterResource(R.drawable.ic_password_hidden)
                                    else painterResource(R.drawable.ic_password_visible),
                                    contentDescription = "Show/Hide password"
                                )
                            }
                        }
                    )

                    TextButton(onClick = { onEvent(LoginScreenEvent.ForgotPassword) }) {
                        Text(
                            text = stringResource(id = R.string.forgot_password),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(Modifier.height(48.dp))

                    MainButton(
                        text = stringResource(id = R.string.login),
                        onClick = {
                            onEvent(
                                LoginScreenEvent.Login(
                                    LoginData(
                                        email = email,
                                        password = password,
                                        type = state.userType
                                    )
                                )
                            )
                        },
                        loading = state.loading
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.dont_have_account),
                            color = Color.Gray
                        )
                        TextButton(onClick = {
                            onEvent(
                                LoginScreenEvent.Navigate(
                                    if (state.userType == UserType.USER) Screen.UserSignUpScreen
                                    else Screen.PharmacySignUpScreen,
                                    popUp = true
                                )
                            )
                        }) {
                            Text(
                                text = stringResource(id = R.string.create_new_account),
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
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
            LoginScreen(
                LoginUiState(),
                {}
            )
        }
    }
}