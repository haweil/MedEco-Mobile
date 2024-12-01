package com.mhss.app.shifak.presentation.user.donate.approve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.use_case.pharmacies.ApproveDonationUseCase
import com.mhss.app.shifak.domain.use_case.pharmacies.GetDonationRequestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ApproveDonationViewModel(
    private val approveRequest: ApproveDonationUseCase,
    private val getDonation: GetDonationRequestUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(ApproveDonationUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ApproveDonationEvent) {
        when(event) {
            is ApproveDonationEvent.Approve -> viewModelScope.launch {
                _uiState.update {
                    it.copy(approveLoading = true)
                }
                try {
                    approveRequest(event.id)
                    _uiState.update {
                        it.copy(approveLoading = false, success = true)
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(approveLoading = false, error = e.localizedMessage)
                    }
                }
            }
            is ApproveDonationEvent.GetOrder -> viewModelScope.launch {
                _uiState.update {
                    it.copy(loading = true)
                }
                try {
                    _uiState.update {
                        it.copy(loading = false, order = getDonation(event.id).data)
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(loading = false, error = e.localizedMessage)
                    }
                }
            }
        }
    }
}