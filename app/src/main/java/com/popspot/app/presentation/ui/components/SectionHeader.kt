package com.popspot.app.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ════════════════════════════════════════════════════════════════════════════
// SectionHeader
// HomeScreen 섹션 상단 "오늘의 핫플 🔥    더보기 >" 헤더 컴포넌트.
// 레퍼런스 이미지: 좌측 굵은 제목 + 우측 "더보기 >" 텍스트버튼
// ════════════════════════════════════════════════════════════════════════════

/**
 * 섹션 구분 헤더.
 *
 * 사용 예:
 * ```kotlin
 * SectionHeader(
 *     title       = "오늘의 핫플 🔥",
 *     onMoreClick = { navController.navigate(...) }
 * )
 * // 더보기 없음
 * SectionHeader(title = "이번 주 공식 행사 🎉")
 * ```
 *
 * @param title 섹션 제목 (이모지 포함 가능)
 * @param onMoreClick "더보기 >" 클릭 콜백. null 이면 버튼 미표시
 * @param modifier 외부 Modifier
 */
@Composable
fun SectionHeader(
    title: String,
    onMoreClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier
            .fillMaxWidth()
            .padding(horizontal = SectionHeaderDefaults.HorizontalPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(
            text       = title,
            style      = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.onBackground
        )

        if (onMoreClick != null) {
            TextButton(
                onClick        = onMoreClick,
                contentPadding = SectionHeaderDefaults.MoreButtonPadding
            ) {
                Text(
                    text       = SectionHeaderDefaults.MoreLabel,
                    style      = MaterialTheme.typography.labelMedium,
                    color      = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/** SectionHeader 레이아웃 상수 */
object SectionHeaderDefaults {
    val HorizontalPadding: Dp = 20.dp
    val MoreButtonPadding     = androidx.compose.foundation.layout.PaddingValues(
        horizontal = 4.dp,
        vertical   = 0.dp
    )
    const val MoreLabel = "더보기 >"
}

// ── Previews ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFF5F3FF)
@Composable
private fun PreviewSectionHeaderWithMore() {
    PopSpotTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier            = Modifier.padding(vertical = 8.dp)
        ) {
            SectionHeader(title = "오늘의 핫플 🔥",        onMoreClick = {})
            SectionHeader(title = "실시간 팝업 트렌드 🔍",  onMoreClick = {})
            SectionHeader(title = "이번 주 공식 행사 🎉",  onMoreClick = {})
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F3FF)
@Composable
private fun PreviewSectionHeaderNoMore() {
    PopSpotTheme {
        SectionHeader(
            title    = "이번 주 공식 행사 🎉",
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
