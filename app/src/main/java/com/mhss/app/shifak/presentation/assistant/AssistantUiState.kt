package com.mhss.app.shifak.presentation.assistant

import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.NetworkResult

data class AssistantUiState(
    val messages: List<AiMessage> = emptyList(),
    val loading: Boolean = false,
    val error: NetworkResult.Failure? = null,
)