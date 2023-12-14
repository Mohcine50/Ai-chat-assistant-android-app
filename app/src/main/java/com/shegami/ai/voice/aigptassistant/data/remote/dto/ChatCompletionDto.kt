package com.shegami.ai.voice.aigptassistant.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.shegami.ai.voice.aigptassistant.domain.model.ChatCompletion

data class ChatCompletionDto(
    @SerializedName("choices")
    val choices: List<Choice>,
    @SerializedName("created")
    val created: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("object")
    val objectX: String,
    @SerializedName("system_fingerprint")
    val systemFingerprint: String,
    @SerializedName("usage")
    val usage: Usage
)

fun ChatCompletionDto.toChatCompletion(): ChatCompletion {
    return ChatCompletion(
        created = created,
        id=  id,
        role = choices[0].message.role,
        content = choices[0].message.content
    )
}



















