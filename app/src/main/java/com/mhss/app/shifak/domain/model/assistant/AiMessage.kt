
package com.mhss.app.shifak.domain.model.assistant

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class AiMessage(
    val content: String,
    val type: AiMessageType,
    val id: Uuid = Uuid.random()
)
enum class AiMessageType {
    USER,
    MODEL
}
