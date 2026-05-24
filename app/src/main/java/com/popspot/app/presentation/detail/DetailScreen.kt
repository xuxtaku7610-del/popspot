package com.popspot.app.presentation.detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.domain.model.PopupStore
import com.popspot.app.presentation.ui.components.EmptyScreen
import com.popspot.app.presentation.ui.components.LoadingScreen

private const val PAGINATION_DOT_COUNT = 6

// ─── Entry point ─────────────────────────────────────────────────────────────

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val store      by viewModel.popupStore.collectAsState()
    val isScrapped by viewModel.isScrapped.collectAsState()
    val isLoading  by viewModel.isLoading.collectAsState()
    val error      by viewModel.error.collectAsState()

    Scaffold(
        // Bottom bar only renders when store is loaded; back arrow is always accessible in header
        bottomBar = {
            if (store != null) {
                DetailBottomBar(
                    isScrapped   = isScrapped,
                    onToggleScrap = viewModel::toggleScrap,
                    onViewSource  = { /* TODO: open store URL when backend provides one */ }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                // Only apply bottom padding from Scaffold — image header extends to top edge
                .padding(bottom = innerPadding.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {
            // Image header is always rendered so the back arrow remains accessible
            DetailImageHeader(onBack = { navController.popBackStack() })

            when {
                isLoading ->
                    LoadingScreen(modifier = Modifier.height(320.dp))

                error != null ->
                    EmptyScreen(
                        message  = error ?: "오류가 발생했어요",
                        modifier = Modifier.height(320.dp)
                    )

                store == null ->
                    EmptyScreen(
                        message  = "정보를 불러오지 못했어요",
                        modifier = Modifier.height(320.dp)
                    )

                else ->
                    DetailContent(
                        store      = store!!,
                        isScrapped = isScrapped
                    )
            }
        }
    }
}

// ─── Image header (back + share overlaid) ────────────────────────────────────

@Composable
private fun DetailImageHeader(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f))
    ) {
        // Placeholder icon centred in the image area
        Icon(
            imageVector        = Icons.Default.Store,
            contentDescription = null,
            tint               = MaterialTheme.colorScheme.tertiary,
            modifier           = Modifier
                .size(72.dp)
                .align(Alignment.Center)
        )

        // Bottom gradient scrim for text legibility if a real image is used later
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.30f))
                    )
                )
        )

        // Navigation row: back arrow ←  |  share icon →
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                    tint               = Color.White
                )
            }
            IconButton(onClick = { /* TODO: share intent */ }) {
                Icon(
                    imageVector        = Icons.Default.Share,
                    contentDescription = "공유",
                    tint               = Color.White
                )
            }
        }

        // Pagination dots — bottom-centre of the image
        PaginationDots(
            total    = PAGINATION_DOT_COUNT,
            current  = 0,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
    }
}

// ─── Pagination dots ─────────────────────────────────────────────────────────

@Composable
private fun PaginationDots(
    total: Int,
    current: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment     = Alignment.CenterVertically
    ) {
        repeat(total) { index ->
            val isActive = index == current
            Box(
                modifier = Modifier
                    .size(if (isActive) 10.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        if (isActive) Color.White
                        else Color.White.copy(alpha = 0.45f)
                    )
            )
        }
    }
}

// ─── Scrollable detail content ────────────────────────────────────────────────

@Composable
private fun DetailContent(
    store: PopupStore,
    isScrapped: Boolean
) {
    var descriptionExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // ── NAVER BLOG badge ─────────────────────────────────────────────
        Text(
            text     = "NAVER BLOG",
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                .padding(horizontal = 8.dp, vertical = 3.dp),
            style      = MaterialTheme.typography.labelSmall,
            color      = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ── Title ────────────────────────────────────────────────────────
        Text(
            text       = store.name.ifBlank { "팝업스토어" },
            style      = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize   = 24.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ── Author chip + date ───────────────────────────────────────────
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Author chip
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector        = Icons.Default.Person,
                    contentDescription = null,
                    tint               = MaterialTheme.colorScheme.primary,
                    modifier           = Modifier.size(14.dp)
                )
                Text(
                    text  = "팝업인터",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Post date
            Text(
                text  = formatDisplayDate(store.startDate),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
        Spacer(modifier = Modifier.height(16.dp))

        // ── Info rows ────────────────────────────────────────────────────
        InfoRow(
            icon  = Icons.Default.CalendarMonth,
            label = "기간",
            value = if (store.startDate.isNotBlank())
                "${formatDisplayDate(store.startDate)} ~ ${formatDisplayDate(store.endDate)}"
            else "미정"
        )
        Spacer(modifier = Modifier.height(10.dp))
        InfoRow(
            icon  = Icons.Default.LocationOn,
            label = "장소",
            value = store.location.address.ifBlank { "미정" }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
        Spacer(modifier = Modifier.height(16.dp))

        // ── Expandable description ────────────────────────────────────────
        Text(
            text     = store.description.ifBlank { "상세 정보가 없습니다." },
            style    = MaterialTheme.typography.bodyMedium,
            color    = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.80f),
            maxLines = if (descriptionExpanded) Int.MAX_VALUE else 4,
            overflow = TextOverflow.Ellipsis
        )

        if (store.description.isNotBlank()) {
            TextButton(
                onClick        = { descriptionExpanded = !descriptionExpanded },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text  = if (descriptionExpanded) "접기" else "더보기",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        // Extra bottom space so content clears the bottom action bar
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ─── Info row (icon + label + value) ─────────────────────────────────────────

@Composable
private fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            imageVector        = icon,
            contentDescription = null,
            tint               = MaterialTheme.colorScheme.primary,
            modifier           = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text  = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
            )
            Text(
                text  = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// ─── Bottom action bar ────────────────────────────────────────────────────────

@Composable
private fun DetailBottomBar(
    isScrapped: Boolean,
    onToggleScrap: () -> Unit,
    onViewSource: () -> Unit
) {
    Surface(
        shadowElevation = 8.dp,
        color           = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment     = Alignment.CenterVertically
        ) {
            // "원문 보기 ↗" — opens original source (URL TBD when backend lands)
            OutlinedButton(
                onClick = onViewSource,
                modifier = Modifier.weight(1f),
                shape   = RoundedCornerShape(10.dp)
            ) {
                Text(text = "원문 보기 ↗")
            }

            // Scrap toggle — filled purple, text changes with scrap state
            Button(
                onClick = onToggleScrap,
                modifier = Modifier.weight(1.5f),
                shape   = RoundedCornerShape(10.dp),
                colors  = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector        = if (isScrapped) Icons.Default.Favorite
                                        else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier           = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isScrapped) "스크랩 해제" else "스크랩",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// ─── Helpers ─────────────────────────────────────────────────────────────────

// "yyyyMMdd" → "yyyy.MM.dd"; falls back to raw string on unexpected format
private fun formatDisplayDate(raw: String): String {
    if (raw.length < 8) return raw
    return "${raw.substring(0, 4)}.${raw.substring(4, 6)}.${raw.substring(6, 8)}"
}
