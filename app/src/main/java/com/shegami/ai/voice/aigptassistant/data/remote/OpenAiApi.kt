package com.shegami.ai.voice.aigptassistant.data.remote

import com.shegami.ai.voice.aigptassistant.common.Constants.OPENAI_API_KEY
import com.shegami.ai.voice.aigptassistant.common.Constants.ORGANIZATION_ID
import com.shegami.ai.voice.aigptassistant.data.remote.dto.ChatCompletionDto
import com.shegami.ai.voice.aigptassistant.data.remote.dto.ImageGenerationDto
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface OpenAiApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $OPENAI_API_KEY"
    )
    @POST("chat/completions")
    suspend fun getTextCompletion(@Body body: RequestBody.TextCompletionBody): ChatCompletionDto

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $OPENAI_API_KEY"
    )
    @POST("images/generations")
    suspend fun imageGeneration(@Body body: RequestBody.ImageBody): ImageGenerationDto
}