package com.shegami.ai.voice.aigptassistant.presentation.generate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@Composable
fun MessagesBoard(
    modifier: Modifier = Modifier,
    messages: List<Message>
) {

    val state = rememberLazyListState()

    LaunchedEffect(messages) {
        val index = if (messages.isEmpty()) 0 else messages.size - 1
        state.animateScrollToItem(index, 0)
    }


    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state
        ) {
            items(messages) { message: Message ->

                if (message.role == "user") {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 3.dp, vertical = 5.dp)
                    ) {
                        UserMessage(userMessage = message.content)
                    }

                } else {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 3.dp, vertical = 5.dp)
                    ) {
                        if (message.content.startsWith("https")) {
                            GeneratedImage(imageUrl = message.content)
                        } else {
                            AssistantMessage(assistantMessage = message.content)
                        }
                    }

                }

            }

        }
    }


}