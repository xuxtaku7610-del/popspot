package com.popspot.app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ════════════════════════════════════════════════════════════════════════════
// BadgeChip
// 게시물 출처를 나타내는 소형 배지 컴포넌트.
// "NAVER BLOG" (primary/보라) / "공식 데이터" (tertiary/에메랄드) 두 종류.
// ════════════════════════════════════════════════════════════════════════════

/** 배지 종류 */
enum class BadgeType {
    /** 네이버 블로그 포스트 — primary(violet) 색상 */
    NaverBlog,
    /** 공공 API 공식 데이터 — tertiary(emerald) 색상 */
    OfficialData
}

/** 배지 배경 스타일 */
enum class BadgeStyle {
    /** 진한 배경 + 흰 텍스트 (배너, 카드 헤더 위) */
    Filled,
    /** 연한 배경 + 진한 텍스트 (카드 본문 내부) */
    Soft
}

/**
 * 게시물 출처 배지.
 *
 * 사용 예:
 * ```kotlin
 * BadgeChip(type = BadgeType.NaverBlog)
 * BadgeChip(type = BadgeType.OfficialData, style = BadgeStyle.Soft)
 * ```
 *
 * @param type [BadgeType] — 배지 종류 (NaverBlog / OfficialData)
 * @param style [BadgeStyle] — Filled(진한) / Soft(연한)
 * @param modifier 외부 Modifier
 */
@Composable
fun BadgeChip(
    type: BadgeType,
    style: BadgeStyle = BadgeStyle.Filled,
    modifier: Modifier = Modifier
) {
    val label = when (type) {
        BadgeType.NaverBlog    -> BadgeChipDefaults.LabelNaverBlog
        BadgeType.OfficialData -> BadgeChipDefaults.LabelOfficialData
    }

    val (bgColor, textColor) = when (type) {
        BadgeType.NaverBlog -> when (style) {
            BadgeStyle.Filled -> Pair(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.onPrimary
            )
            BadgeStyle.Soft -> Pair(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.primary
            )
        }
        BadgeType.OfficialData -> when (style) {
            BadgeStyle.Filled -> Pair(
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.onTertiary
            )
            BadgeStyle.Soft -> Pair(
                MaterialTheme.colorScheme.tertiaryContainer,
                MaterialTheme.colorScheme.tertiary
            )
        }
    }

    BadgeLabel(
        label     = label,
        bgColor   = bgColor,
        textColor = textColor,
        modifier  = modifier
    )
}

/**
 * 문자열을 직접 받는 저수준 배지.
 * 커스텀 레이블이 필요할 때 사용.
 */
@Composable
fun BadgeChip(
    label: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    BadgeLabel(label = label, bgColor = bgColor, textColor = textColor, modifier = modifier)
}

@Composable
private fun BadgeLabel(
    label: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text       = label,
        modifier   = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(bgColor)
            .padding(
                horizontal = BadgeChipDefaults.HorizontalPadding,
                vertical   = BadgeChipDefaults.VerticalPadding
            ),
        style      = MaterialTheme.typography.labelSmall,
        color      = textColor,
        fontWeight = FontWeight.Bold
    )
}

/** BadgeChip 레이아웃 상수 */
object BadgeChipDefaults {
    val HorizontalPadding: Dp = 8.dp
    val VerticalPadding: Dp   = 3.dp
    const val LabelNaverBlog    = "NAVER BLOG"
    const val LabelOfficialData = "공식 데이터"
}

// ── Previews ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewBadgeChipFilled() {
    PopSpotTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment     = Alignment.CenterVertically,
            modifier              = Modifier.padding(16.dp)
        ) {
            BadgeChip(type = BadgeType.NaverBlog,    style = BadgeStyle.Filled)
            BadgeChip(type = BadgeType.OfficialData, style = BadgeStyle.Filled)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewBadgeChipSoft() {
    PopSpotTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment     = Alignment.CenterVertically,
            modifier              = Modifier.padding(16.dp)
        ) {
            BadgeChip(type = BadgeType.NaverBlog,    style = BadgeStyle.Soft)
            BadgeChip(type = BadgeType.OfficialData, style = BadgeStyle.Soft)
        }
    }
}
