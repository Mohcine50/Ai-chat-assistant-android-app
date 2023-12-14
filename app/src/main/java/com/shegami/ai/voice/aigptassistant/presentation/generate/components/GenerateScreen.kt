package com.shegami.ai.voice.aigptassistant.presentation.generate.components

import androidx.compose.runtime.*
import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.shegami.ai.voice.aigptassistant.R
import com.shegami.ai.voice.aigptassistant.presentation.generate.GenerateEvent
import com.shegami.ai.voice.aigptassistant.presentation.generate.GenerateViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenerateScreen(
    viewModel: GenerateViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(R.drawable.bot),
                contentDescription = "intro",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(120.dp)
            )

            Text(
                text = "Assistant",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            MessagesBoard(
                messages = state.messages,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(CornerSize(15.dp)))
                    .background(Color.LightGray)
                    .padding(2.dp)
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                // This stop button is for stopping the text type writing animation
                // TODO: add typewriting animation and complete the implementation
                Button(
                    onClick = {
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF87171),
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = "STOP",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                /** if (state?.recording == true) {
                Button(
                onClick = {
                viewModel?.onEvent(
                GenerateEvent.GenerateResponseEvent(
                state?.prompt ?: "", state.messages
                )
                )
                },
                //contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
                colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
                )
                ) {
                Image(
                painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                .data(data = R.drawable.voice_loading)
                .build(),
                imageLoader = imageLoader
                ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
                )
                }
                } else {
                Button(
                onClick = {
                viewModel?.onEvent(GenerateEvent.Record)
                },
                //contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
                colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
                )
                ) {
                Image(
                painterResource(R.drawable.recording_icon),
                contentDescription = "intro",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
                )
                }
                }*/

                MessageInput(
                    text = state.prompt ?: "",
                    onValueChange = {},
                    onFocusChange = {},
                    modifier = Modifier.weight(2f)
                )

                Button(
                    onClick = {
                        viewModel.onEvent(
                            GenerateEvent.ClearMessages
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (state.messages.isEmpty()) Color(0xFFA3A3A3) else Color(
                            0xFF34d399
                        ),
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 7.dp)
                ) {

                    Text(
                        text = "CLEAR",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }

            }
        }
    }
}
