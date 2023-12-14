package com.shegami.ai.voice.aigptassistant.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("content")
    val content: String,
    @SerializedName("role")
    val role: String
)