package com.mhss.app.shifak.presentation.pharmacy.network

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto
import com.mhss.app.shifak.data.remote.pharmacy.model.NetworkRequestSender
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import com.mhss.app.shifak.domain.model.pharmacy.PharmacyBranch
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun NetworkRequestsScreen(
    state: PharmacyNetworkUiState,
    modifier: Modifier = Modifier,
    onEvent: (PharmacyNetworkEvent) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(state.error) {
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.active_requests),
                onNavigateUp = {
                    onEvent(PharmacyNetworkEvent.NavigateUp)
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(state.requests, key = { it.id }) { item ->
                NetworkRequestCard(data = item) {
                    onEvent(PharmacyNetworkEvent.ApproveRequest(item))
                }
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
private fun NetworkRequestsScreenPreview() {
    MedEcoTheme {
        NetworkRequestsScreen(
            state = PharmacyNetworkUiState(
                requests = listOf(
                    GetNetworkRequestsDto(
                        id = 5351, description = "3x Panadol Extra", sender = NetworkRequestSender(
                            branch = PharmacyBranch(
                                id = 6853,
                                phone = "(115) 445-6841",
                                lat = null,
                                lng = null,
                                address = null,
                                pharmacy = null
                            ), pharmacy = Pharmacy(
                                hotline = "12345",
                                id = 7717,
                                name = "El Ezaby pharmacy",
                                logo = null,
                                branches = listOf()
                            )
                        )

                    )
                )
            ),
            onEvent = {}
        )
    }
}

