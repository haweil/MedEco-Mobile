package com.mhss.app.shifak.presentation.pharmacy.network

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun MakeNetworkRequestScreen(
    state: PharmacyNetworkUiState,
    modifier: Modifier = Modifier,
    onEvent: (PharmacyNetworkEvent) -> Unit
) {
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    LaunchedEffect(state) {
        if (state.navigateUp) {
            Toast.makeText(context, context.getString(R.string.request_success), Toast.LENGTH_SHORT).show()
            onEvent(PharmacyNetworkEvent.NavigateUp)
        }
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.make_request),
                onNavigateUp = {
                    onEvent(PharmacyNetworkEvent.NavigateUp)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            MainTextField(
                value = description,
                hint = stringResource(R.string.description),
                onValueChange = { description = it},
                modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp).padding(12.dp)
            )
            MainButton(
                text = stringResource(R.string.confirm),
                modifier = Modifier.padding(12.dp),
                loading = state.loading,
                onClick = {
                    onEvent(PharmacyNetworkEvent.MakeRequest(description))
                }
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun MakeNetworkRequestScreenPreview() {
    MedEcoTheme {
        MakeNetworkRequestScreen(
            state = PharmacyNetworkUiState(),
            onEvent = {}
        )
    }
}

