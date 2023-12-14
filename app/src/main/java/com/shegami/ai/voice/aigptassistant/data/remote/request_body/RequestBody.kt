package com.shegami.ai.voice.aigptassistant.data.remote.request_body

sealed class RequestBody {
    data class ImageBody(val prompt: String, val size: String = "512x512") : RequestBody()
    data class TextCompletionBody(
        val model: String = "gpt-3.5-turbo",
        val messages: List<Message>
    ) : RequestBody()
}
