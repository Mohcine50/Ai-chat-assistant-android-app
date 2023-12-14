package com.shegami.ai.voice.aigptassistant.domain.repository

import com.shegami.ai.voice.aigptassistant.data.remote.dto.ChatCompletionDto
import com.shegami.ai.voice.aigptassistant.data.remote.dto.ImageGenerationDto
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.RequestBody
import com.shegami.ai.voice.aigptassistant.domain.model.ChatCompletion
import com.shegami.ai.voice.aigptassistant.domain.model.ImageGeneration

interface OpenAiRepository {

    suspend fun getTextCompletion(body: RequestBody.TextCompletionBody): ChatCompletionDto
    suspend fun generateImage(body: RequestBody.ImageBody): ImageGenerationDto

}