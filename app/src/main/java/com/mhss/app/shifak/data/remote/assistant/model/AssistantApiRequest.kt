package com.mhss.app.shifak.data.remote.assistant.model

import com.mhss.app.shifak.domain.model.assistant.AiMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantMessageRequestBody(
    @SerialName("message")
    val message: String,
    @SerialName("chat_history")
    val messages: List<AiMessageDto>
)

@Serializable
data class AiMessageDto(
    @SerialName("message")
    val message: String,
    @SerialName("role")
    val role: String
)

fun List<AiMessage>.toAssistantRequestBody(): AssistantMessageRequestBody {
    return AssistantMessageRequestBody(
        message = last().content,
        messages =  take(size - 1).map {
            AiMessageDto(
                message = it.content,
                role = it.type.apiRole
            )
        }
    )
}