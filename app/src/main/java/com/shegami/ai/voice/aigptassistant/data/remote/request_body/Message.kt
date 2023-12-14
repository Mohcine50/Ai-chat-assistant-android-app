package com.shegami.ai.voice.aigptassistant.data.remote.request_body


import com.google.gson.annotations.SerializedName

data class Message(
    val content: String,
    val role: String
)