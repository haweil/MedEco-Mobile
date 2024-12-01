package com.mhss.app.shifak.presentation.assistant

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.AiMessageType
import com.mhss.app.shifak.domain.model.assistant.NetworkResult
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun AssistantScreen(
    state: AssistantUiState,
    messages: List<AiMessage>,
    onEvent: (AssistantEvent) -> Unit,
) {
    val loading = state.loading
    val error = state.error
    var text by rememberSaveable { mutableStateOf("") }

    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        bottomBar = {
            AiChatBar(
                text = text,
                enabled = !loading && text.isNotBlank(),
                onTextChange = { text = it },
                loading = loading,
                onSend = {
                    onEvent(
                        AssistantEvent.SendMessage(
                            AiMessage(
                                text,
                                AiMessageType.USER
                            )
                        )
                    )
                    text = ""
                    keyboardController?.hide()
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LeftToRight {
                LazyColumn(
                    state = lazyListState,
                    reverseLayout = true,
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(key = 1) { Spacer(Modifier.height(20.dp)) }
                    error?.let { error ->
                        item(key = 2) {
                            Card(
                                shape = RoundedCornerShape(18.dp),
                                border = BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.onErrorContainer
                                ),
                                colors = CardDefaults.cardColors(
                                    contentColor = MaterialTheme.colorScheme.errorContainer
                                ),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = error.toUserMessage(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.CenterHorizontally),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onErrorContainer
                                )
                            }
                        }
                    }
                    items(messages, key = { it.id }) { message ->
                        MessageCard(
                            message = message
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun LeftToRight(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr, content)
}

@Composable
fun NetworkResult.Failure.toUserMessage(): String {
    return when (this) {
        NetworkResult.InternetError -> stringResource(R.string.no_internet_connection)
        is NetworkResult.OtherError -> message ?: stringResource(R.string.unexpected_error)
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun AssistantScreenPreview() {
    MedEcoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            val demoText = remember {
                LoremIpsum(60).values.first()
            }
            AssistantScreen(
                messages = emptyList(),
                state = AssistantUiState(
                    listOf(
                        AiMessage(
                            demoText,
                            AiMessageType.USER
                        ),
                        AiMessage(
                            demoText,
                            AiMessageType.MODEL
                        )
                    )
                ),
                onEvent = {}
            )
        }
    }
}