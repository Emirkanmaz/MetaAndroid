package com.emirkanmaz.littlelemonfinal.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = yellow,
    secondary = pink,
)

@Composable
fun LittleLemonFinalTheme(
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}