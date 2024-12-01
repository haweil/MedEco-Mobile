package com.mhss.app.shifak.domain.repository.assistant

import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.NetworkResult

interface AssistantApi {

    suspend fun sendMessage(messages: List<AiMessage>): NetworkResult<AiMessage>
}