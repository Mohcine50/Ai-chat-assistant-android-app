package com.shegami.ai.voice.aigptassistant.presentation.generate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AssistantMessage(
    assistantMessage: String
) {

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp))
            .background(Color(0xFF34d399))
            .padding(5.dp)
    ) {
        Text(text = assistantMessage, fontSize = 15.sp)
    }

}