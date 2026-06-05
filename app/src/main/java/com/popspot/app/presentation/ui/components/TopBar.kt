package com.popspot.app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.popspot.app.presentation.ui.theme.PopSpotRadius
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ════════════════════════════════════════════════════════════════════════════
// PopSpot — TopBar Composables
// HomeTopBar    : 로고 + 도시 칩 + 알림 벨
// PopupFeedTopBar : 뒤로가기 + 타이틀 + 검색
// ════════════════════════════════════════════════════════════════════════════

/**
 * 홈 화면 TopBar.
 *
 * @param cityName          도시 칩에 표시할 이름 (예: "서울")
 * @param onCityClick       도시 칩 클릭 콜백
 * @param onNotificationClick 알림 벨 클릭 콜백
 */
@Composable
fun HomeTopBar(
    cityName: String,
    onCityClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ─── 왼쪽: 로고 ───────────────────────────────────────────────
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 보라 박스 아이콘 — 28×28dp, Chip radius(8dp), 흰 "P"
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(PopSpotRadius.Chip))
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "P",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                // 브랜드명 — titleLarge (20sp / Bold)
                Text(
                    text = "Pop-Spot",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // ─── 오른쪽: 도시 칩 + 알림 벨 ──────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // 도시 칩 — pill 형태, Gray100(outlineVariant) 배경
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(PopSpotRadius.ChipPill))
                        .background(MaterialTheme.colorScheme.outlineVariant)
                        .clickable(onClick = onCityClick)
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = cityName,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // 알림 벨 — outline 스타일, 터치 타겟 44dp 이상 확보
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "알림",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

/**
 * 팝업 트렌드 화면 TopBar (뒤로가기 + 가운데 타이틀 + 검색).
 *
 * @param title         가운데 표시할 화면 제목 (예: "팝업 트렌드")
 * @param onBackClick   뒤로가기 클릭 콜백
 * @param onSearchClick 검색 아이콘 클릭 콜백
 */
@Composable
fun PopupFeedTopBar(
    title: String,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        // Box 레이아웃으로 가운데 타이틀 정확하게 정렬
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            // 뒤로가기 — 왼쪽 고정
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "뒤로가기",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // 타이틀 — 정중앙 (Box contentAlignment = Center 활용)
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            // 검색 — 오른쪽 고정
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "검색",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

// ════════════════════════════════════════════════════════════════════════════
// Previews
// ════════════════════════════════════════════════════════════════════════════

@Preview(showBackground = true, name = "HomeTopBar")
@Composable
private fun HomeTopBarPreview() {
    PopSpotTheme {
        HomeTopBar(
            cityName = "서울",
            onCityClick = {},
            onNotificationClick = {}
        )
    }
}

@Preview(showBackground = true, name = "PopupFeedTopBar")
@Composable
private fun PopupFeedTopBarPreview() {
    PopSpotTheme {
        PopupFeedTopBar(
            title = "팝업 트렌드",
            onBackClick = {},
            onSearchClick = {}
        )
    }
}
