package com.popspot.app.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ════════════════════════════════════════════════════════════════════════════
// PopSpotButton
// Primary (filled violet) / Secondary (outlined) 두 가지 버튼.
// 레퍼런스 이미지: 상세 화면의 "원문 보기 ↗" / "♥ 스크랩 해제" 버튼 스타일
// ════════════════════════════════════════════════════════════════════════════

/**
 * Primary 버튼 — primary(violet) 배경 + 흰 텍스트.
 *
 * 사용 예:
 * ```kotlin
 * PopSpotPrimaryButton(text = "팝업 둘러보기", onClick = { ... })
 * PopSpotPrimaryButton(text = "♥ 스크랩 해제", onClick = { ... }, useSecondaryColor = true)
 * ```
 *
 * @param text 버튼 레이블
 * @param onClick 클릭 콜백
 * @param modifier 외부 Modifier
 * @param enabled 활성화 여부
 * @param useSecondaryColor true 면 secondary(pink) 색상 사용 (스크랩 해제 버튼 등)
 */
@Composable
fun PopSpotPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    useSecondaryColor: Boolean = false
) {
    val containerColor = if (useSecondaryColor)
        MaterialTheme.colorScheme.secondary
    else
        MaterialTheme.colorScheme.primary

    val contentColor = if (useSecondaryColor)
        MaterialTheme.colorScheme.onSecondary
    else
        MaterialTheme.colorScheme.onPrimary

    Button(
        onClick        = onClick,
        modifier       = modifier.height(PopSpotButtonDefaults.Height),
        enabled        = enabled,
        shape          = MaterialTheme.shapes.small,   // 8dp
        colors         = ButtonDefaults.buttonColors(
            containerColor         = containerColor,
            contentColor           = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor   = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(
            horizontal = PopSpotButtonDefaults.HorizontalPadding,
            vertical   = PopSpotButtonDefaults.VerticalPadding
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text       = text,
            style      = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Secondary 버튼 — 흰 배경 + primary 테두리 + primary 텍스트.
 * 레퍼런스: 상세 화면 "원문 보기 ↗" 버튼
 *
 * @param text 버튼 레이블
 * @param onClick 클릭 콜백
 * @param modifier 외부 Modifier
 * @param enabled 활성화 여부
 */
@Composable
fun PopSpotSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick        = onClick,
        modifier       = modifier.height(PopSpotButtonDefaults.Height),
        enabled        = enabled,
        shape          = MaterialTheme.shapes.small,
        colors         = ButtonDefaults.outlinedButtonColors(
            contentColor           = MaterialTheme.colorScheme.primary,
            disabledContentColor   = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        border         = BorderStroke(
            width = PopSpotButtonDefaults.BorderWidth,
            color = if (enabled) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.outline
        ),
        contentPadding = PaddingValues(
            horizontal = PopSpotButtonDefaults.HorizontalPadding,
            vertical   = PopSpotButtonDefaults.VerticalPadding
        )
    ) {
        Text(
            text       = text,
            style      = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/** PopSpotButton 레이아웃 상수 */
object PopSpotButtonDefaults {
    val Height: Dp             = 48.dp
    val HorizontalPadding: Dp  = 24.dp
    val VerticalPadding: Dp    = 12.dp
    val BorderWidth: Dp        = 1.5.dp
}

// ── Previews ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFF5F3FF)
@Composable
private fun PreviewButtons() {
    PopSpotTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier            = Modifier.padding(20.dp)
        ) {
            PopSpotPrimaryButton(
                text     = "팝업 둘러보기",
                onClick  = {},
                modifier = Modifier.fillMaxWidth()
            )
            PopSpotPrimaryButton(
                text              = "♥ 스크랩 해제",
                onClick           = {},
                modifier          = Modifier.fillMaxWidth(),
                useSecondaryColor = true
            )
            PopSpotSecondaryButton(
                text     = "원문 보기 ↗",
                onClick  = {},
                modifier = Modifier.fillMaxWidth()
            )
            PopSpotPrimaryButton(
                text     = "비활성 버튼",
                onClick  = {},
                modifier = Modifier.fillMaxWidth(),
                enabled  = false
            )
        }
    }
}
