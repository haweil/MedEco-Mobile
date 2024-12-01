package com.mhss.app.shifak.domain.use_case.assistant

import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.NetworkResult
import com.mhss.app.shifak.domain.repository.assistant.AssistantApi
import org.koin.core.annotation.Single
import java.io.IOException

@Single
class SendAiMessageUseCase(
    private val api: AssistantApi
) {
    suspend operator fun invoke(
        messages: List<AiMessage>
    ): NetworkResult<AiMessage> {
        return try {
            api.sendMessage(messages)
        } catch (e: IOException) {
            e.printStackTrace()
            NetworkResult.InternetError
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.OtherError()
        }
    }
}