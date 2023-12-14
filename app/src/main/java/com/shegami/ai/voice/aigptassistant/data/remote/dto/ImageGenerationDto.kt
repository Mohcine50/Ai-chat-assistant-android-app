package com.shegami.ai.voice.aigptassistant.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.shegami.ai.voice.aigptassistant.domain.model.ImageGeneration

data class ImageGenerationDto(
    @SerializedName("created")
    val created: Int,
    @SerializedName("data")
    val `data`: List<Data>
)

fun ImageGenerationDto.toImageGeneration(): ImageGeneration {
    return ImageGeneration(
        created = created,
        url = data[0].url,
    )
}