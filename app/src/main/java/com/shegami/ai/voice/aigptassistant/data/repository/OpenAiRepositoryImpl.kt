package com.shegami.ai.voice.aigptassistant.data.repository

import com.shegami.ai.voice.aigptassistant.data.remote.OpenAiApi
import com.shegami.ai.voice.aigptassistant.data.remote.dto.ChatCompletionDto
import com.shegami.ai.voice.aigptassistant.data.remote.dto.ImageGenerationDto
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.RequestBody
import com.shegami.ai.voice.aigptassistant.domain.repository.OpenAiRepository
import javax.inject.Inject

class OpenAiRepositoryImpl @Inject constructor(
    private val api: OpenAiApi
): OpenAiRepository {
    override suspend fun getTextCompletion(body: RequestBody.TextCompletionBody): ChatCompletionDto {
        return api.getTextCompletion(body)
    }

    override suspend fun generateImage(body: RequestBody.ImageBody): ImageGenerationDto {
        return api.imageGeneration(body)
    }
}