package com.mhss.app.shifak.presentation.auth.signup

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.auth.PharmacySignUpData
import com.mhss.app.shifak.domain.model.auth.SignUpData
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.ui.theme.PrimaryColor
import com.mhss.app.shifak.presentation.ui.theme.SecondaryColor
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.util.Gender
import com.mhss.app.shifak.util.UserType

@Composable
fun PharmacySignUpScreen(
    state: SignUpUiState,
    onEvent: (SignUpScreenEvent) -> Unit,
) {
    val context = LocalContext.current
    var pharmacyName by rememberSaveable { mutableStateOf("") }
    var hotline by rememberSaveable { mutableStateOf("") }
    var businessNumber by rememberSaveable { mutableStateOf("") }
    var taxNumber by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConf by rememberSaveable { mutableStateOf("") }
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }
    var imageUri: Uri? by rememberSaveable { mutableStateOf(null) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri ?: imageUri
        }
    )

    LaunchedEffect(state.done, state.error) {
        if (state.done) {
            onEvent(SignUpScreenEvent.Navigate(Screen.PharmacyMainScreen, true))
        }
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.create_new_account),
                onNavigateUp = {
                    onEvent(SignUpScreenEvent.NavigateUp)
                },
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
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 48.dp, topStart = 48.dp))
                    .background(Color.White)
                    .padding(start = 32.dp, end = 32.dp, top = 24.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    Modifier
                        .clickable {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                        .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), CircleShape)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    SubcomposeAsyncImage(
                        model = imageUri,
                        contentDescription = null,
                        modifier = Modifier.size(86.dp),
                        contentScale = ContentScale.Crop,
                        error = {
                            Icon(
                                painter = painterResource(R.drawable.ic_camera),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .align(Alignment.Center),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                }
                MainTextField(
                    value = pharmacyName,
                    onValueChange = { pharmacyName = it },
                    hint = stringResource(id = R.string.pharmacy_name),
                    keyboardType = KeyboardType.Text,
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = hotline,
                    onValueChange = { hotline = it },
                    hint = stringResource(id = R.string.hotline),
                    keyboardType = KeyboardType.Phone
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = businessNumber,
                    onValueChange = { businessNumber = it },
                    hint = stringResource(id = R.string.commercial_number),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = taxNumber,
                    onValueChange = { taxNumber = it },
                    hint = stringResource(id = R.string.tax_number),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = email,
                    onValueChange = { email = it },
                    hint = stringResource(id = R.string.email),
                    keyboardType = KeyboardType.Email
                )
                Spacer(Modifier.height(18.dp))
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
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = passwordConf,
                    onValueChange = { passwordConf = it },
                    hint = stringResource(id = R.string.confirm_password),
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
                Spacer(Modifier.height(36.dp))

                MainButton(
                    text = stringResource(id = R.string.sign_up),
                    loading = state.loading,
                    onClick = {
                        onEvent(
                            SignUpScreenEvent.SignUp(
                                SignUpData(
                                    fullName = pharmacyName,
                                    phone = hotline,
                                    nationalId = "${(1..1000000).random()}",
                                    email = email,
                                    password = password,
                                    passwordConf = passwordConf,
                                    birthDate = 0,
                                    gender = Gender.MALE,
                                    type = UserType.PHARMACY,
                                    pharmacyName = pharmacyName,
                                    pharmacyHotline = hotline,
                                    pharmacyLogo = imageUri,
                                )
                            )
                        )
                    },
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.already_have_account),
                        color = Color.Gray
                    )
                    TextButton(onClick = {
                        onEvent(SignUpScreenEvent.Navigate(
                            Screen.LoginScreen(state.userType)
                        ))
                    }) {
                        Text(
                            text = stringResource(id = R.string.login),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun PharmacySignUpScreenPreview() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            PharmacySignUpScreen(
                SignUpUiState(),
                {}
            )
        }
    }
}