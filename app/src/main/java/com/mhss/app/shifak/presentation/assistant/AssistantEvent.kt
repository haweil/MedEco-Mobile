package com.mhss.app.shifak.presentation.assistant

import com.mhss.app.shifak.domain.model.assistant.AiMessage

sealed interface AssistantEvent {
    data class SendMessage(val message: AiMessage): AssistantEvent
}
