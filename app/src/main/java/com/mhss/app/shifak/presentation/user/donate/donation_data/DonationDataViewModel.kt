package com.mhss.app.shifak.presentation.user.donate.donation_data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.data.remote.user.model.CreateOrderBody
import com.mhss.app.shifak.data.remote.user.model.IdWithQuantity
import com.mhss.app.shifak.domain.use_case.user.GetAllDrugsUseCase
import com.mhss.app.shifak.domain.use_case.user.MakeDonateOrderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DonationDataViewModel(
    private val makeOrder: MakeDonateOrderUseCase,
    private val getDrugs: GetAllDrugsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DonationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                try {
                    it.copy(drugs = getDrugs())
                } catch (e: Exception) {
                    it.copy(error = e.localizedMessage)
                }
            }
        }
    }

    fun onEvent(event: DonationEvent) {
        when (event) {
            is DonationEvent.MakeOrder -> viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        loading = true
                    )
                }
                val data = event.data.map {
                    IdWithQuantity(
                        it.key.id,
                        it.value
                    )
                }
                try {
                    val result = makeOrder(
                        CreateOrderBody(
                            branchId = event.branchId,
                            ids = data
                        )
                    )
                    _uiState.update {
                        it.copy(
                            loading = false,
                            orderId = result.orderId
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = e.localizedMessage
                        )
                    }
                }
            }
            else -> Unit
        }
    }
}