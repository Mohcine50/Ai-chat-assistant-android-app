package com.shegami.ai.voice.aigptassistant.domain.model

data class ImageGeneration(
    val created: Int,
    val url: String,
    val role: String = "assistant"
)