package com.shegami.ai.voice.aigptassistant.presentation.generate

import com.shegami.ai.voice.aigptassistant.data.remote.request_body.Message

sealed class GenerateEvent {
    data class GenerateResponseEvent(val prompt: String, val messages: List<Message>) :
        GenerateEvent()

    object ClearMessages : GenerateEvent()
    object Record : GenerateEvent()
}
