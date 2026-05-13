package com.popspot.app.presentation.scrap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.presentation.ui.components.EmptyScreen

private val SCRAP_FILTER_CHIPS = listOf("전체", "팝업", "공식 행사")

// ─── Entry point ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrapScreen(
    navController: NavController,
    viewModel: ScrapViewModel = hiltViewModel()
) {
    val selectedFilter by viewModel.selectedFilter.collectAsState()
    val scrappedItems  by viewModel.scrappedItems.collectAsState()

    // Edit mode: shows X-delete icon per item instead of the heart quick-unscrap icon
    var isEditMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ScrapTopBar(
                isEditMode    = isEditMode,
                onEditToggle  = { isEditMode = !isEditMode }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            // ── Filter chips ──────────────────────────────────────────────
            ScrapFilterRow(
                chips          = SCRAP_FILTER_CHIPS,
                selectedFilter = selectedFilter,
                onFilterSelect = viewModel::filterItems,
                modifier       = Modifier.padding(vertical = 8.dp)
            )

            // ── Content ───────────────────────────────────────────────────
            if (scrappedItems.isEmpty()) {
                EmptyScreen(
                    message  = "스크랩한 항목이 없어요",
                    modifier = Modifier.weight(1f)
                )
            } else {
                ScrapList(
                    items      = scrappedItems,
                    isEditMode = isEditMode,
                    onUnscrap  = { viewModel.removeScrap(it) },
                    modifier   = Modifier.weight(1f)
                )
            }
        }
    }
}

// ─── Top App Bar ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrapTopBar(
    isEditMode: Boolean,
    onEditToggle: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text       = "스크랩",
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            TextButton(onClick = onEditToggle) {
                Text(
                    text  = if (isEditMode) "완료" else "편집",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

// ─── Filter chip row ──────────────────────────────────────────────────────────

@Composable
private fun ScrapFilterRow(
    chips: List<String>,
    selectedFilter: String,
    onFilterSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier              = modifier,
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chips) { chip ->
            FilterChip(
                selected  = chip == selectedFilter,
                onClick   = { onFilterSelect(chip) },
                label     = { Text(text = chip) },
                colors    = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor     = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

// ─── Scrap list ───────────────────────────────────────────────────────────────

@Composable
private fun ScrapList(
    items: List<ScrapItem>,
    isEditMode: Boolean,
    onUnscrap: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier            = modifier,
        contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
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

// ─── Scrap item card ──────────────────────────────────────────────────────────

@Composable
private fun ScrapItemCard(
    item: ScrapItem,
    isEditMode: Boolean,
    onUnscrap: () -> Unit
) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ── Thumbnail placeholder (80 × 80dp, TealWave tint) ─────────
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = Icons.Default.Article,
                    contentDescription = null,
                    tint               = MaterialTheme.colorScheme.tertiary,
                    modifier           = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // ── Text content ──────────────────────────────────────────────
            Column(modifier = Modifier.weight(1f)) {
                // Source badge — label differs by source string
                val badgeLabel = if (item.source == "NAVER_BLOG") "NAVER BLOG" else "공식 데이터"
                Text(
                    text     = badgeLabel,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    style      = MaterialTheme.typography.labelSmall,
                    color      = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Title — single-line clamp
                Text(
                    text       = item.title,
                    style      = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Date
                Text(
                    text  = formatScrapDate(item.date),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            // ── Action icon — heart (default) or X (edit mode) ───────────
            IconButton(onClick = onUnscrap) {
                if (isEditMode) {
                    Icon(
                        imageVector        = Icons.Default.Close,
                        contentDescription = "삭제",
                        tint               = MaterialTheme.colorScheme.error
                    )
                } else {
                    Icon(
                        imageVector        = Icons.Default.Favorite,
                        contentDescription = "스크랩 해제",
                        tint               = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

// ─── Helpers ─────────────────────────────────────────────────────────────────

// "yyyyMMdd" → "yyyy.MM.dd"; falls back to raw if too short
private fun formatScrapDate(raw: String): String {
    if (raw.length < 8) return raw
    return "${raw.substring(0, 4)}.${raw.substring(4, 6)}.${raw.substring(6, 8)}"
}
