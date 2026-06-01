package com.popspot.app.presentation.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.domain.model.Event
import com.popspot.app.domain.model.PopupStore
import com.popspot.app.presentation.ui.components.ErrorScreen
import com.popspot.app.presentation.ui.components.LoadingScreen

// ─── Entry point ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBar() }
    ) { innerPadding ->
        when {
            uiState.isLoading ->
                LoadingScreen(modifier = Modifier.padding(innerPadding))

            uiState.error != null ->
                ErrorScreen(
                    message  = uiState.error ?: "",
                    modifier = Modifier.padding(innerPadding)
                )

            else ->
                HomeContent(
                    uiState  = uiState,
                    modifier = Modifier.padding(innerPadding)
                )
        }
    }
}

// ─── Top App Bar ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            Text(
                text       = "PopSpot",
                modifier   = Modifier.padding(start = 16.dp),
                color      = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                fontSize   = 22.sp
            )
        },
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text          = "서울",
                    style         = MaterialTheme.typography.titleSmall,
                    fontWeight    = FontWeight.SemiBold,
                    // 수정 3: 도시 선택 텍스트 2sp 확대 (14sp → 16sp), letterSpacing 비례 조정
                    fontSize      = 16.sp,
                    letterSpacing = (-0.32).sp
                )
                Icon(
                    imageVector        = Icons.Default.ArrowDropDown,
                    contentDescription = "위치 선택"
                )
            }
        },
        actions = {
            // 수정 5: Stack(Box) + Positioned로 빨간 알림 뱃지 추가 (항상 표시, Phase 2에서 조건부 처리)
            Box {
                IconButton(onClick = { /* TODO: 알림 화면 */ }) {
                    Icon(
                        imageVector        = Icons.Default.Notifications,
                        contentDescription = "알림"
                    )
                }
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-8).dp, y = 8.dp)
                        .background(Color.Red, CircleShape)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        // 수정 1: 헤더 상단 여백을 14dp로 감소 (기본 WindowInsets는 status bar 높이를 포함)
        windowInsets = WindowInsets(top = 14.dp)
    )
}

// ─── Scrollable content ───────────────────────────────────────────────────────

@Composable
private fun HomeContent(
    uiState:  HomeUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        // ── 1. 오늘의 핫플 🔥 ─────────────────────────────────────────
        Spacer(modifier = Modifier.height(12.dp))
        SectionHeader(title = "오늘의 핫플 🔥", onMoreClick = {})
        FeaturedBannerCard(store = uiState.hotStores.firstOrNull())

        // ── 2. 실시간 팝업 트렌드 🔍 ──────────────────────────────────
        Spacer(modifier = Modifier.height(28.dp))
        SectionHeader(title = "실시간 팝업 트렌드 🔍", onMoreClick = {})
        TrendStoreRow(stores = uiState.trendStores.take(3))

        // ── 3. 이번 주 공식 행사 🎉 ────────────────────────────────────
        Spacer(modifier = Modifier.height(28.dp))
        SectionHeader(title = "이번 주 공식 행사 🎉", onMoreClick = {})
        OfficialEventCard(event = uiState.officialEvents.firstOrNull())
    }
}

// ─── Section header ───────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String, onMoreClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(
            text       = title,
            style      = MaterialTheme.typography.titleMedium,
            // 수정 2: FontWeight.Bold → FontWeight.SemiBold
            fontWeight = FontWeight.SemiBold
        )
        TextButton(onClick = onMoreClick) {
            Text(
                text  = "더보기 >",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

// ─── Section 1: Featured banner card ─────────────────────────────────────────

@Composable
private fun FeaturedBannerCard(store: PopupStore?) {
    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(210.dp),
        shape     = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.62f)
                        )
                    )
                )
                .padding(22.dp)
        ) {
            // 수정 3: Column → Row로 변경하여 우측 썸네일 placeholder 추가
            Row(
                modifier          = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text  = store?.name ?: "팝업스토어 탐색하기",
                        color = MaterialTheme.colorScheme.onPrimary,
                        // 수정 2: FontWeight.Bold → FontWeight.SemiBold, letterSpacing 추가 (22 × -0.02 = -0.44)
                        fontWeight    = FontWeight.SemiBold,
                        fontSize      = 22.sp,
                        letterSpacing = (-0.44).sp,
                        maxLines      = 2,
                        overflow      = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text  = store?.location?.address ?: "지금 핫한 팝업을 찾아보세요",
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.82f),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (store != null) {
                        Text(
                            text  = "${store.startDate} ~ ${store.endDate}",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.65f),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = { /* TODO: detail({ store?.id }) */ },
                        colors  = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor   = MaterialTheme.colorScheme.primary
                        ),
                        // 수정 3: 버튼 shape을 직사각형 → pill 모양으로 변경
                        shape          = RoundedCornerShape(99.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        // 수정 3: 버튼 텍스트에 화살표 추가
                        Text(text = "자세히 보기 →", fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 수정 3: 카드 우측 썸네일 placeholder (80×80dp, borderRadius 12dp)
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                )
            }
        }
    }
}

// ─── Section 2: Trend store row ───────────────────────────────────────────────

@Composable
private fun TrendStoreRow(stores: List<PopupStore>) {
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(stores) { store ->
            TrendStoreCard(store = store)
        }
    }
}

@Composable
private fun TrendStoreCard(store: PopupStore) {
    Card(
        modifier  = Modifier
            .width(130.dp)
            .height(165.dp),
        shape     = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = Icons.Default.Store,
                    contentDescription = null,
                    tint               = MaterialTheme.colorScheme.tertiary,
                    modifier           = Modifier.padding(20.dp)
                )
            }
            Text(
                text       = store.name,
                modifier   = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                style      = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines   = 2,
                overflow   = TextOverflow.Ellipsis
            )
        }
    }
}

// ─── Section 3: Official event card ──────────────────────────────────────────

@Composable
private fun OfficialEventCard(event: Event?) {
    if (event == null) return

    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape     = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text       = formatEventDate(event.startDate),
                    style      = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color      = MaterialTheme.colorScheme.onTertiary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text  = "공식 데이터",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = event.title,
                    style      = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text     = event.place,
                    style    = MaterialTheme.typography.bodySmall,
                    color    = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// ─── Helpers ─────────────────────────────────────────────────────────────────

private fun formatEventDate(raw: String): String {
    if (raw.length < 8) return raw
    val month = raw.substring(4, 6)
    val day   = raw.substring(6, 8)
    return "$month.$day"
}
