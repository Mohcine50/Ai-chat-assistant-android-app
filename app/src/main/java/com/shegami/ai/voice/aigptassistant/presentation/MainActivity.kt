package com.shegami.ai.voice.aigptassistant.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shegami.ai.voice.aigptassistant.presentation.generate.components.GenerateScreen
import com.shegami.ai.voice.aigptassistant.presentation.splash.components.SplashScreen
import com.shegami.ai.voice.aigptassistant.presentation.util.Screen
import com.shegami.ai.voice.aigptassistant.ui.theme.AiGptAssistantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiGptAssistantTheme {
                Surface(
                    color = androidx.compose.material.MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {

                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }
                        composable(route = Screen.GenerateScreen.route) {
                            GenerateScreen()
                        }

                    }
                }
            }
        }
    }
}
