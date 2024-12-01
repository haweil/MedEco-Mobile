package com.mhss.app.shifak.presentation.pharmacy.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.data.remote.pharmacy.model.NetworkRequest
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.pharmacies.ApproveNetworkRequestUseCase
import com.mhss.app.shifak.domain.use_case.pharmacies.GetAllNetworkRequestsUseCase
import com.mhss.app.shifak.domain.use_case.pharmacies.MakeNetworkRequestUseCase
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PharmacyNetworkViewModel(
    private val makeRequest: MakeNetworkRequestUseCase,
    private val approveRequest: ApproveNetworkRequestUseCase,
    private val getRequests: GetAllNetworkRequestsUseCase,
    private val fetchRequests: Boolean = false,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PharmacyNetworkUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            if (fetchRequests) {
                _uiState.update { it.copy(loading = true) }
                try {
                    val requests = getRequests()
                    _uiState.update { it.copy(loading = false, requests = requests) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(loading = false, error = "Error getting requests") }
                }
            }
        }
    }


    fun onEvent(event: PharmacyNetworkEvent) {
        when (event) {
            is PharmacyNetworkEvent.MakeRequest -> viewModelScope.launch {
                _uiState.update { it.copy(loading = true) }
                try {
                    makeRequest(NetworkRequest(event.description))
                    _uiState.update { it.copy(navigateUp = true, loading = false) }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = "Error making request"
                        )
                    }
                }
            }
            is PharmacyNetworkEvent.ApproveRequest -> viewModelScope.launch {
                _uiState.update { it.copy(loading = true) }
                try {
                    approveRequest(event.request)
                    val newRequests = _uiState.value.requests - event.request
                    _uiState.update { it.copy(loading = false, requests = newRequests) }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = "Error approving request"
                        )
                    }
                }

            }

            else -> Unit
        }
    }
}