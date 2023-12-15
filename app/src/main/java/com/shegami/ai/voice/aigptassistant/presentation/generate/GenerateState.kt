package com.shegami.ai.voice.aigptassistant.presentation.generate

import com.shegami.ai.voice.aigptassistant.data.remote.request_body.Message

data class GenerateState(
    val messages: List<Message> = emptyList(),
    var prompt: String = "",
)
