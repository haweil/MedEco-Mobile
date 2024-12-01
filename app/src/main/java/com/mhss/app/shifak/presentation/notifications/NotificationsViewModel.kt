package com.mhss.app.shifak.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.use_case.pharmacies.ApproveNetworkRequestUseCase
import com.mhss.app.shifak.domain.use_case.pharmacies.GetAllNetworkRequestsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NotificationsViewModel(
    private val getNetworkRequests: GetAllNetworkRequestsUseCase,
    private val approveRequest: ApproveNetworkRequestUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val requests = try {
                getNetworkRequests()
            } catch (_: Exception) {
                emptyList()
            }
            _uiState.update {
                it.copy(notifications = requests.map { request ->
                    NetworkRequestNotification(request)
                })
            }
        }
    }

    fun onEvent(event: NotificationsScreenEvent) {
        when (event) {
            is NotificationsScreenEvent.ApproveRequest -> viewModelScope.launch {
                try {
                    approveRequest(event.request)
                    val newNotifications = _uiState.value.notifications.filter {
                        !(it is NetworkRequestNotification && it.request == event.request)
                    }
                    _uiState.update {
                        it.copy(notifications = newNotifications)
                    }
                } catch (_: Exception) { }
            }
        }
    }

}