package com.example.kakaoapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val KakaoYellow = Color(0xFFFEE500)
val BgDark = Color(0xFF111111)
val CardDark = Color(0xFF1E1E1E)
val CardDarker = Color(0xFF2A2A2A)
val CardDarkest = Color(0xFF3A3A3A)
val TextWhite = Color(0xFFFFFFFF)
val TextGray = Color(0xFF9CA3AF)
val TextGrayDark = Color(0xFF6B7280)
val TextGrayDarker = Color(0xFF4B5563)
val RedBadge = Color(0xFFEF4444)
val BorderGray = Color(0xFF374151)

private val DarkColorScheme = darkColorScheme(
    background = BgDark,
    surface = CardDark,
    primary = KakaoYellow,
    onPrimary = Color.Black,
    onBackground = TextWhite,
    onSurface = TextWhite,
)

@Composable
fun KakaoPayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
