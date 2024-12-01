package com.mhss.app.shifak.presentation.assistant

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.AiMessageType
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlin.uuid.ExperimentalUuidApi


@Composable
fun LazyItemScope.MessageCard(
    message: AiMessage,
) {
    val isUser = message.type == AiMessageType.USER
    Row(
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = if (isUser) 8.dp else 48.dp,
                start = if (isUser) 48.dp else 8.dp,
                bottom = 4.dp,
                top = 8.dp
            )
            .animateItem(
                fadeInSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = if (isUser) 20.dp else 4.dp,
                topEnd = if (isUser) 4.dp else 20.dp,
                bottomStart = if (isUser) 24.dp else 14.dp,
                bottomEnd = if (isUser) 14.dp else 20.dp
            ),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isUser) MaterialTheme.colorScheme.primary
                else Color(0xFFDCDCDC)
            )
        ) {

            MarkdownText(
                markdown = message.content,
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = if (isUser) 12.dp else 8.dp,
                    end = if (isUser) 8.dp else 12.dp,
                    bottom = 12.dp
                ),
                linkColor = Color.Blue,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (isUser) Color.White else Color.Black
                )
            )

        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun MessageCardPreview() {
    MedEcoTheme {
        val demoText = remember {
            LoremIpsum(60).values.first()
        }
        LazyColumn {
            item {
                MessageCard(
                    message = AiMessage(
                        demoText,
                        AiMessageType.USER
                    )
                )
            }
            item {
                MessageCard(
                    message = AiMessage(
                        demoText,
                        AiMessageType.MODEL
                    )
                )
            }
        }
    }
}