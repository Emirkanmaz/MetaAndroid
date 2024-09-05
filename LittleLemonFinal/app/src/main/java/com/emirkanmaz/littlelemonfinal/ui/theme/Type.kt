package com.emirkanmaz.littlelemonfinal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),
    displayMedium = TextStyle(
        color = charcoal,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        color = green
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        color = green
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
)