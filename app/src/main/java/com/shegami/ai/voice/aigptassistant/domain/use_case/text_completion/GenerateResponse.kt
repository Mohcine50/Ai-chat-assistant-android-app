package com.shegami.ai.voice.aigptassistant.domain.use_case.text_completion

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import com.shegami.ai.voice.aigptassistant.common.Resource
import com.shegami.ai.voice.aigptassistant.data.remote.dto.toChatCompletion
import com.shegami.ai.voice.aigptassistant.data.remote.dto.toImageGeneration
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.Message
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.RequestBody
import com.shegami.ai.voice.aigptassistant.domain.model.ChatCompletion
import com.shegami.ai.voice.aigptassistant.domain.repository.OpenAiRepository
import com.shegami.ai.voice.aigptassistant.domain.util.GeneratedResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.coroutineContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GenerateResponse @Inject constructor(
    private val repo: OpenAiRepository
) {

    operator fun invoke(
        prompt: String,
        messages: List<Message>
    ): Flow<Resource<Message>> = flow {

        emit(Resource.Loading())
        try {
            val textBody = RequestBody.TextCompletionBody(
                model = "gpt-3.5-turbo",
                messages = listOf(
                    Message(
                        role = "user",
                        content = "Does this message want to generate an AI picture, image, art or anything similar? $prompt . Simply answer with a yes or no."
                    )
                )
            )

            val chatCompletionChecker = repo.getTextCompletion(textBody).toChatCompletion()

            Log.d("GENERATERESPONSE", chatCompletionChecker.toString())

            if (chatCompletionChecker.content.trim().toLowerCase().contains("yes")) {

                val imageBody = RequestBody.ImageBody(prompt = prompt)
                val generatedImage = repo.generateImage(imageBody).toImageGeneration()

                emit(Resource.Success(Message(generatedImage.url, generatedImage.role)))
            } else {
                val textCompletionBody = RequestBody.TextCompletionBody(messages = messages)
                val generatedText = repo.getTextCompletion(textCompletionBody).toChatCompletion()

                emit(
                    Resource.Success(
                        Message(
                            content = generatedText.content,
                            role = generatedText.role
                        )
                    )
                )
            }


        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "Error Found"))

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the Server"))
        }
    }

}