package com.mhss.app.shifak.presentation.pharmacy.network

import androidx.compose.runtime.Immutable
import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto

@Immutable
data class PharmacyNetworkUiState(
    val loading: Boolean = false,
    val navigateUp: Boolean = false,
    val requests: List<GetNetworkRequestsDto> = emptyList(),
    val error: String? = null
)
