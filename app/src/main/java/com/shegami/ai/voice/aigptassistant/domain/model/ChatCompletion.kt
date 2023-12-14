package com.shegami.ai.voice.aigptassistant.domain.model

import com.google.gson.annotations.SerializedName
import com.shegami.ai.voice.aigptassistant.data.remote.dto.Choice
import com.shegami.ai.voice.aigptassistant.data.remote.dto.Usage

data class ChatCompletion(
    val created: Int,
    val id: String,
    val role: String,
    val content: String
)