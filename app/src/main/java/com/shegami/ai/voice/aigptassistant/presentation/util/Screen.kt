package com.shegami.ai.voice.aigptassistant.presentation.util

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object GenerateScreen : Screen("generate_screen")

}
