package com.shegami.ai.voice.aigptassistant.presentation.generate

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
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

    private val speechRecognizer: SpeechRecognizer by lazy {
        SpeechRecognizer.createSpeechRecognizer(
            context,
            ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService")
        )
    }


    init {
        setupSpeechRecognizer()
    }

    fun onEvent(event: GenerateEvent) {

        when (event) {

            is GenerateEvent.GenerateResponseEvent -> {
                //stopSpeechRecognition()
                //if (_state.value.prompt?.isNotBlank() == true) {
                Log.d("GENERATE", "GENERATING")
                _state.value = state.value.copy(
                    messages = _state.value.messages + Message(event.prompt, "user")
                )
                Log.d("MESSAGES", _state.value.toString())
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
                        }
                    }
                }.launchIn(viewModelScope)

                //}

            }

            is GenerateEvent.ClearMessages -> {
                _state.value = GenerateState(messages = emptyList())
            }

            is GenerateEvent.Record -> {
                viewModelScope.launch {
                    Log.d("RECORD", "RECORDING")
                    startSpeechRecognition()
                }
            }

        }

    }

    private fun startSpeechRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        speechRecognizer.startListening(intent)
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognizer.destroy()
    }

    private fun stopSpeechRecognition() {
        speechRecognizer.stopListening()
    }

    private fun setupSpeechRecognizer() {

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
            }

            override fun onBeginningOfSpeech() {
                Log.d("RECORD", "onBeginningOfSpeech")
                _state.value = state.value.copy(
                    recording = true
                )
            }

            override fun onRmsChanged(rmsdB: Float) {
            }

            override fun onBufferReceived(buffer: ByteArray?) {
            }

            override fun onEndOfSpeech() {
                Log.d("RECORD", "onEndOfSpeech")
                _state.value = state.value.copy(
                    recording = false
                )
            }

            override fun onError(error: Int) {
                Log.d("ERROR", error.toString())
            }

            override fun onResults(results: Bundle?) {

                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                matches?.firstOrNull()?.let {
                    _state.value = _state.value.copy(
                        prompt = it
                    )
                    _state.value = state.value.copy(
                        messages = _state.value.messages + Message(it, "user")
                    )
                }

            }

            override fun onPartialResults(partialResults: Bundle?) {
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
            }

        })

    }

}