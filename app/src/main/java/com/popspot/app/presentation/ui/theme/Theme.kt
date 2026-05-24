package com.popspot.app.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary          = FestivalPurple,
    secondary        = AccentPink,
    tertiary         = TealWave,
    background       = SoftIvory,
    surface          = MysticLavender,
    onPrimary        = OnPrimary,
    onSecondary      = OnSecondary,
    onTertiary       = OnTertiary,
    onBackground     = OnSecondary,
    onSurface        = OnSecondary
)

@Composable
fun PopSpotTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography  = Typography,
        content     = content
    )
}
