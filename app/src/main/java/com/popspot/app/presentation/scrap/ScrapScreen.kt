package com.popspot.app.presentation.scrap

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.presentation.navigation.BottomNavItem

private val SCRAP_TABS = listOf("전체", "팝업", "공식 행사")

// ─── Design tokens ─────────────────────────────────────────────────────────────
private val Purple      = Color(0xFF7C5CBF)
private val LightPurple = Color(0xFFF0EEFF)
private val EventGreen  = Color(0xFF2E7D32)
private val EventMint   = Color(0xFFD5F5E3)
private val TextSub     = Color(0xFF888888)
private val BgGray      = Color(0xFFF5F3FF)

// ─── Entry point ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrapScreen(
    navController: NavController,
    viewModel: ScrapViewModel = hiltViewModel()
) {
    val selectedFilter by viewModel.selectedFilter.collectAsState()
    val scrappedItems by viewModel.scrappedItems.collectAsState()
    var isEditMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ScrapTopBar(
                isEditMode = isEditMode,
                onEditToggle = { isEditMode = !isEditMode }
            )
        },
        containerColor = BgGray,
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ScrapChipRow(
                tabs = SCRAP_TABS,
                selected = selectedFilter,
                onSelect = viewModel::filterItems
            )

            if (scrappedItems.isEmpty()) {
                ScrapEmptyState(
                    onExplore = { navController.navigate(BottomNavItem.Home.route) },
                    modifier = Modifier.weight(1f)
                )
            } else {
                ScrapList(
                    items = scrappedItems,
                    isEditMode = isEditMode,
                    onUnscrap = viewModel::removeScrap,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// ─── Top App Bar ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrapTopBar(isEditMode: Boolean, onEditToggle: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "스크랩", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        },
        actions = {
            TextButton(onClick = onEditToggle) {
                Text(
                    text       = if (isEditMode) "완료" else "편집",
                    color      = Purple,
                    style      = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        windowInsets = WindowInsets(0),
        colors       = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
    )
}

// ─── 스크랩 필터 칩 (PopupFeedScreen ChipRow와 동일 스타일) ────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrapChipRow(
    tabs: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tabs) { tab ->
            val isSelected = tab == selected
            FilterChip(
                selected = isSelected,
                onClick  = { onSelect(tab) },
                label    = {
                    Text(
                        text       = tab,
                        style      = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                // 선택: Violet600 배경 + 흰 텍스트 / 미선택: 흰 배경 + 회색 테두리
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor     = Color.White,
                    containerColor         = Color.White,
                    labelColor             = Color(0xFF555555)
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled             = true,
                    selected            = isSelected,
                    borderColor         = Color(0xFFCCCCCC),
                    selectedBorderColor = Color.Transparent,
                    borderWidth         = 1.dp,
                    selectedBorderWidth = 0.dp
                )
            )
        }
    }
}

// ─── 스크랩 목록 ───────────────────────────────────────────────────────────────

@Composable
private fun ScrapList(
    items: List<ScrapItem>,
    isEditMode: Boolean,
    onUnscrap: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier            = modifier,
        contentPadding      = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items, key = { it.id }) { item ->
            ScrapItemCard(
                item       = item,
                isEditMode = isEditMode,
                onUnscrap  = { onUnscrap(item.id) }
            )
        }
    }
}

// ─── 스크랩 카드 (썸네일 + 배지 + 제목 + 날짜 + 위치) ────────────────────────

@Composable
private fun ScrapItemCard(
    item: ScrapItem,
    isEditMode: Boolean,
    onUnscrap: () -> Unit
) {
    val isBlog       = item.source == "NAVER_BLOG"
    val thumbColor   = if (isBlog) LightPurple else EventMint
    val thumbEmoji   = if (isBlog) "📰" else "🎉"
    val badgeLabel   = if (isBlog) "NAVER BLOG" else "공식 행사"
    val badgeColor   = if (isBlog) Purple else EventGreen
    val locationText = if (isBlog) "네이버 블로그" else "한국관광공사"

    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 썸네일 60×60
            Box(
                modifier         = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(thumbColor),
                contentAlignment = Alignment.Center
            ) {
                Text(text = thumbEmoji, fontSize = 26.sp)
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // 소스 배지
                Text(
                    text       = badgeLabel,
                    modifier   = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(badgeColor.copy(alpha = 0.12f))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    style      = MaterialTheme.typography.labelSmall,
                    color      = badgeColor,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(5.dp))

                // 제목 — 최대 2줄
                Text(
                    text       = item.title,
                    style      = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(6.dp))

                // 날짜 + 위치
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text(
                        text  = "📅 ${formatScrapDate(item.date)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSub
                    )
                    Text(
                        text     = "📍 $locationText",
                        style    = MaterialTheme.typography.labelSmall,
                        color    = TextSub,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(Modifier.width(4.dp))

            // 편집 모드: X 버튼 / 기본: 하트 버튼
            IconButton(onClick = onUnscrap) {
                Icon(
                    imageVector        = if (isEditMode) Icons.Default.Close else Icons.Default.Favorite,
                    contentDescription = if (isEditMode) "삭제" else "스크랩 해제",
                    tint               = if (isEditMode) MaterialTheme.colorScheme.error else Purple,
                    modifier           = Modifier.size(20.dp)
                )
            }
        }
    }
}

// ─── 빈 상태 ────────────────────────────────────────────────────────────────────

@Composable
private fun ScrapEmptyState(onExplore: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier            = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "🔖", fontSize = 64.sp)

        Spacer(Modifier.height(20.dp))

        Text(
            text       = "아직 스크랩한 항목이 없어요",
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text  = "마음에 드는 팝업을 스크랩해보세요",
            style = MaterialTheme.typography.bodySmall,
            color = TextSub
        )

        Spacer(Modifier.height(28.dp))

        Button(
            onClick        = onExplore,
            colors         = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor   = Color.White
            ),
            shape          = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 36.dp, vertical = 12.dp)
        ) {
            Text(text = "팝업 둘러보기", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}

// ─── Helpers ───────────────────────────────────────────────────────────────────

// "yyyyMMdd" → "yyyy.MM.dd"
private fun formatScrapDate(raw: String): String {
    if (raw.length < 8) return raw
    return "${raw.substring(0, 4)}.${raw.substring(4, 6)}.${raw.substring(6, 8)}"
}
