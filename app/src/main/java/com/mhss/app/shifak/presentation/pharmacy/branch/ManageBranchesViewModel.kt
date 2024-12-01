package com.mhss.app.shifak.presentation.pharmacy.branch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.use_case.pharmacies.CreatePharmacyBranchUseCase
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ManageBranchesViewModel(
    private val createBranch: CreatePharmacyBranchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ManageBranchesUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ManageBranchesEvent) {
        when(event) {
            is ManageBranchesEvent.AddBranch -> viewModelScope.launch {
                _uiState.update { it.copy(loading = true) }
                try {
                    createBranch(event.branch)
                    _uiState.update { it.copy(loading = false) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(loading = false, error = e.localizedMessage) }
                }
            }
        }
    }
}