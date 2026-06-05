package com.popspot.app.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ════════════════════════════════════════════════════════════════════════════
// PopSpot Design System — Theme
// Color + Typography + Shape 을 MaterialTheme 으로 연결
// ════════════════════════════════════════════════════════════════════════════

private val LightColorScheme = lightColorScheme(
    // Primary — 딥 바이올렛 (#7C3AED)
    primary             = Violet600,
    onPrimary           = OnPrimary,
    primaryContainer    = Violet100,
    onPrimaryContainer  = Violet800,

    // Secondary — 핑크 (#EC4899) : 스크랩 버튼, 하트
    secondary             = Pink500,
    onSecondary           = OnSecondary,
    secondaryContainer    = Pink100,
    onSecondaryContainer  = Pink500,

    // Tertiary — 에메랄드 (#10B981) : 공식 데이터 배지
    tertiary             = Emerald500,
    onTertiary           = OnTertiary,
    tertiaryContainer    = Emerald100,
    onTertiaryContainer  = Emerald500,

    // Background / Surface
    background       = Violet50,
    onBackground     = Gray900,
    surface          = White,
    onSurface        = Gray900,
    surfaceVariant   = Violet100,
    onSurfaceVariant = Gray600,

    // Outline / Divider
    outline      = Gray200,
    outlineVariant = Gray100,

    // Error
    error   = ErrorRed,
    onError = White
)

private val DarkColorScheme = darkColorScheme(
    primary             = Violet500,
    onPrimary           = White,
    primaryContainer    = Violet800,
    onPrimaryContainer  = Violet100,
    secondary             = Pink500,
    onSecondary           = White,
    secondaryContainer    = Pink100,
    onSecondaryContainer  = Pink500,
    tertiary             = Emerald500,
    onTertiary           = White,
    tertiaryContainer    = Emerald500,
    onTertiaryContainer  = Emerald100,
    background       = Gray900,
    onBackground     = White,
    surface          = Gray900,
    onSurface        = White,
    surfaceVariant   = Gray600,
    onSurfaceVariant = Gray200,
    outline      = Gray600,
    error   = ErrorRed,
    onError = White
)

/**
 * PopSpot 앱 전역 테마.
 *
 * @param darkTheme 다크 모드 여부 (기본 false — 라이트 전용)
 * @param content 하위 컴포저블
 */
@Composable
fun PopSpotTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        shapes      = PopSpotShapes,
        content     = content
    )
}
