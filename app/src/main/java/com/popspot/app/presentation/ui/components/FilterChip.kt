package com.popspot.app.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ════════════════════════════════════════════════════════════════════════════
// PopSpotFilterChip
// 해시태그 스타일 필터 칩 컴포넌트.
// 선택: primary(violet) 배경 + 흰 Bold 텍스트
// 미선택: 흰 배경 + outline(회색 테두리) + 보조 텍스트
// ════════════════════════════════════════════════════════════════════════════

/**
 * 해시태그 필터 칩 단일 아이템.
 *
 * 사용 예:
 * ```kotlin
 * PopSpotFilterChip(label = "성수", selected = true, onClick = { ... })
 * ```
 *
 * @param label 칩에 표시할 텍스트 (# 접두어 자동 추가)
 * @param selected 선택 상태
 * @param onClick 클릭 콜백
 * @param modifier 외부 Modifier
 */
@Composable
fun PopSpotFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (selected)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.surface

    val contentColor = if (selected)
        MaterialTheme.colorScheme.onPrimary
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    val border = if (selected) null
    else BorderStroke(
        width = FilterChipDefaults.BorderWidth,
        color = MaterialTheme.colorScheme.outline
    )

    Surface(
        onClick      = onClick,
        modifier     = modifier,
        shape        = MaterialTheme.shapes.medium,    // 16dp — 이미지 칩 스타일
        color        = containerColor,
        contentColor = contentColor,
        border       = border,
        tonalElevation = 0.dp,
        shadowElevation = if (selected) FilterChipDefaults.SelectedElevation else 0.dp
    ) {
        Text(
            text       = "#$label",
            modifier   = Modifier.padding(
                horizontal = FilterChipDefaults.HorizontalPadding,
                vertical   = FilterChipDefaults.VerticalPadding
            ),
            style      = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

/**
 * 해시태그 필터 칩 수평 스크롤 행.
 *
 * @param chips 칩 레이블 목록
 * @param selectedChip 현재 선택된 칩 레이블
 * @param onChipSelected 선택 변경 콜백
 * @param modifier 외부 Modifier
 */
@Composable
fun FilterChipRow(
    chips: List<String>,
    selectedChip: String,
    onChipSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier              = modifier,
        contentPadding        = PaddingValues(horizontal = FilterChipDefaults.RowHorizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(FilterChipDefaults.ChipSpacing)
    ) {
        items(chips) { chip ->
            PopSpotFilterChip(
                label    = chip,
                selected = chip == selectedChip,
                onClick  = { onChipSelected(chip) }
            )
        }
    }
}

/** FilterChip 레이아웃 상수 */
object FilterChipDefaults {
    val HorizontalPadding: Dp   = 14.dp
    val VerticalPadding: Dp     = 8.dp
    val BorderWidth: Dp         = 1.dp
    val SelectedElevation: Dp   = 2.dp
    val RowHorizontalPadding: Dp = 16.dp
    val ChipSpacing: Dp         = 8.dp
}

// ── Previews ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFF5F3FF)
@Composable
private fun PreviewFilterChipRow() {
    PopSpotTheme {
        val chips = listOf("전체", "성수", "한정판", "캐릭터", "팝업카페")
        var selected by remember { mutableStateOf("전체") }

        FilterChipRow(
            chips          = chips,
            selectedChip   = selected,
            onChipSelected = { selected = it },
            modifier       = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewFilterChipStates() {
    PopSpotTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            PopSpotFilterChip(label = "전체",  selected = true,  onClick = {})
            PopSpotFilterChip(label = "성수",  selected = false, onClick = {})
            PopSpotFilterChip(label = "한정판", selected = false, onClick = {})
        }
    }
}
