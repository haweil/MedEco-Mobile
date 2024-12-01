package com.mhss.app.shifak.data.remote.assistant.model

import com.mhss.app.shifak.data.remote.assistant.AssistantConstants.MESSAGE_MODEL_TYPE
import com.mhss.app.shifak.data.remote.assistant.AssistantConstants.MESSAGE_USER_TYPE
import com.mhss.app.shifak.domain.model.assistant.AiMessageType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantApiResponse(
    @SerialName("text")
    val text: String
)

val AiMessageType.apiRole
    get() = if (this == AiMessageType.USER) MESSAGE_USER_TYPE else MESSAGE_MODEL_TYPE