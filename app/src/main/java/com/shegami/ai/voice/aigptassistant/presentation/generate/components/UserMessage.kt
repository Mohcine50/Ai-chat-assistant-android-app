package com.shegami.ai.voice.aigptassistant.presentation.generate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserMessage(
    userMessage: String
) {


    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 8.dp))
            .background(Color.White)
            .padding(5.dp)
    ) {
        Text(text = userMessage, fontSize = 15.sp, textAlign = TextAlign.End)
    }


}


















