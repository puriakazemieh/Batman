package com.kazemieh.www.batman.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.cardBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1B1A1A) else Color(0xFFFAFAFA)

val ColorScheme.black40: Color
    @Composable
    get() = Color(0x66000000)

val ColorScheme.white60: Color
    @Composable
    get() = Color(0x99FFFFFF)