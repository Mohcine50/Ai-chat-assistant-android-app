package com.shegami.ai.voice.aigptassistant.presentation.generate

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shegami.ai.voice.aigptassistant.common.Resource
import com.shegami.ai.voice.aigptassistant.data.remote.request_body.Message
import com.shegami.ai.voice.aigptassistant.domain.use_case.text_completion.GenerateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val generateResponse: GenerateResponse,
    private val context: Context
) : ViewModel() {


    private val _state = mutableStateOf(GenerateState())
    val state: State<GenerateState> = _state


    fun onEvent(event: GenerateEvent) {

        when (event) {

            is GenerateEvent.GenerateResponseEvent -> {
                //stopSpeechRecognition()
                //if (_state.value.prompt?.isNotBlank() == true) {
                Log.d("GENERATE", "GENERATING")
                _state.value = state.value.copy(
                    messages = _state.value.messages + Message(event.prompt, "user"),
                    prompt = ""
                )
                Log.d("MESSAGES", _state.value.messages.toString())
                generateResponse(event.prompt, event.messages).onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            Log.d("GENERATE", "Loading")
                        }

                        is Resource.Success -> {
                            Log.d("GENERATE", "Result Success")
                            _state.value = state.value.copy(
                                messages = _state.value.messages + result.data as Message
                            )
                        }

                        is Resource.Error -> {
                            Log.d("GENERATE", result.message.toString())
                            Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.launchIn(viewModelScope)

                //}

            }

            is GenerateEvent.ClearMessages -> {
                _state.value = GenerateState(messages = emptyList())
            }

            is GenerateEvent.InputPrompt -> {
                _state.value = state.value.copy(
                    prompt = event.prompt
                )
            }

        }

    }


}