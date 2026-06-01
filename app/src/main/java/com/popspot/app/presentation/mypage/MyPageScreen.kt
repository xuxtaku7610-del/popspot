package com.popspot.app.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ─── 상수 ────────────────────────────────────────────────────────────────────
private val CARD_SHAPE       = RoundedCornerShape(16.dp)
private val CARD_PADDING     = 16.dp
private val SECTION_SPACING  = 12.dp
private val AVATAR_SIZE      = 72.dp
private val AVATAR_FONT_SIZE = 28.sp
private const val APP_VERSION = "1.0.0"

// 수정 1: 카드 공통 색상 상수
private val BACKGROUND_COLOR  = Color(0xFFF8F7FF)  // 아주 연한 보라
private val CARD_BORDER_COLOR = Color(0xFFEDE9FE)  // 카드 테두리

// 수정 2, 3, 4: 브랜드·중립 색상
private val BRAND_PURPLE  = Color(0xFF7B5CF5)
private val ICON_GRAY     = Color(0xFF9CA3AF)       // 메뉴 아이콘·섹션 타이틀
private val CHIP_BG_COLOR = Color(0xFFF3F0FF)       // 태그·편집 버튼 배경
private val DESTRUCTIVE   = Color(0xFFEF4444)       // 로그아웃

// ─── 진입 Composable ─────────────────────────────────────────────────────────

@Composable
fun MyPageScreen(
    navController: NavController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MyPageContent(
        uiState              = uiState,
        onToggleNotification = viewModel::toggleNotification
    )
}

// ─── 순수 UI (Preview / 테스트용) ────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageContent(
    uiState: MyPageUiState,
    onToggleNotification: () -> Unit
) {
    val context = LocalContext.current
    // 추가 2: 로그아웃 다이얼로그 표시 상태
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title        = { Text(text = "마이페이지", fontWeight = FontWeight.SemiBold) },
                colors       = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    // 수정 1: 앱바 배경을 Scaffold 배경과 통일
                    containerColor = BACKGROUND_COLOR
                ),
                windowInsets = WindowInsets(top = 14.dp)
            )
        },
        // 수정 1: Scaffold 배경을 아주 연한 보라로 변경
        containerColor = BACKGROUND_COLOR
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = CARD_PADDING)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(SECTION_SPACING)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // ── 1. 프로필 카드 ──────────────────────────────────────────────
            ProfileCard(
                name        = "사용자",
                email       = "user@popspot.com",
                onEditClick = {
                    Toast.makeText(context, "프로필 편집 — 준비 중", Toast.LENGTH_SHORT).show()
                }
            )

            // ── 2. 통계 카드 ────────────────────────────────────────────────
            StatsCard(
                scrapCount        = uiState.scrapCount,
                recentSearchCount = uiState.recentSearchCount,
                reviewCount       = uiState.reviewCount
            )

            // ── 3. 내 활동 섹션 ─────────────────────────────────────────────
            SectionCard(title = "내 활동") {
                ActivityRow(
                    icon    = Icons.Outlined.Bookmarks,
                    label   = "스크랩 목록",
                    onClick = {
                        Toast.makeText(context, "스크랩 탭에서 확인하세요", Toast.LENGTH_SHORT).show()
                    }
                )
                SectionDivider()
                ActivityRow(
                    icon    = Icons.Outlined.History,
                    label   = "최근 검색 기록",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
                SectionDivider()
                ActivityRow(
                    icon    = Icons.Outlined.RateReview,
                    label   = "내 리뷰",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
            }

            // ── 4. 앱 설정 섹션 ─────────────────────────────────────────────
            SectionCard(title = "앱 설정") {
                ActivityRow(
                    icon    = Icons.Outlined.Map,
                    label   = "기본 지도 타입",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
                SectionDivider()
                NotificationRow(
                    enabled  = uiState.notificationEnabled,
                    onToggle = onToggleNotification
                )
                SectionDivider()
                ActivityRow(
                    icon    = Icons.Outlined.DarkMode,
                    label   = "다크 모드",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
            }

            // ── 5. 앱 정보 섹션 ─────────────────────────────────────────────
            SectionCard(title = "앱 정보") {
                ActivityRow(
                    icon    = Icons.Outlined.Info,
                    label   = "공지사항",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
                SectionDivider()
                // 추가 2: 이용약관
                ActivityRow(
                    icon    = Icons.Outlined.Article,
                    label   = "이용약관",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
                SectionDivider()
                // 추가 2: 개인정보처리방침
                ActivityRow(
                    icon    = Icons.Outlined.Policy,
                    label   = "개인정보처리방침",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
                SectionDivider()
                ActivityRow(
                    icon    = Icons.Outlined.Info,
                    label   = "오픈소스 라이선스",
                    onClick = { Toast.makeText(context, "준비 중", Toast.LENGTH_SHORT).show() }
                )
                SectionDivider()
                VersionRow(version = APP_VERSION)
                SectionDivider()
                // 추가 2: 로그아웃 (isDestructive → 아이콘·텍스트 빨간색)
                ActivityRow(
                    icon          = Icons.Outlined.ExitToApp,
                    label         = "로그아웃",
                    isDestructive = true,
                    onClick       = { showLogoutDialog = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // 추가 2: 로그아웃 확인 다이얼로그
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title   = { Text("로그아웃") },
            text    = { Text("정말 로그아웃 하시겠어요?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    // TODO: 실제 로그아웃 처리
                    Toast.makeText(context, "로그아웃 — 준비 중", Toast.LENGTH_SHORT).show()
                }) {
                    Text("로그아웃", color = DESTRUCTIVE)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("취소")
                }
            }
        )
    }
}

// ─── 프로필 카드 ─────────────────────────────────────────────────────────────

@Composable
private fun ProfileCard(
    name: String,
    email: String,
    onEditClick: () -> Unit
) {
    Card(
        shape     = CARD_SHAPE,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        // 수정 1: 카드 배경 흰색, 테두리 추가
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        border    = BorderStroke(0.5.dp, CARD_BORDER_COLOR)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(CARD_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier         = Modifier
                    .size(AVATAR_SIZE)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text          = name.firstOrNull()?.uppercaseChar()?.toString() ?: "U",
                    color         = MaterialTheme.colorScheme.onPrimary,
                    fontSize      = AVATAR_FONT_SIZE,
                    fontWeight    = FontWeight.SemiBold,
                    letterSpacing = (-0.56).sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = name,
                    style      = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text  = email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )

                // 추가 1: 관심 카테고리 태그 (이메일 아래 가로 스크롤)
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    listOf("#뷰티", "#캐릭터", "#한정판").forEach { tag ->
                        Box(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .clip(RoundedCornerShape(99.dp))
                                .background(CHIP_BG_COLOR)
                                .padding(horizontal = 10.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text       = tag,
                                fontSize   = 11.sp,
                                color      = BRAND_PURPLE,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 수정 4: TextButton → 라운드 pill 컨테이너로 교체
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .background(CHIP_BG_COLOR)
                    .clickable(onClick = onEditClick)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text       = "편집",
                    fontSize   = 12.sp,
                    color      = BRAND_PURPLE,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// ─── 통계 카드 ───────────────────────────────────────────────────────────────

@Composable
private fun StatsCard(
    scrapCount: Int,
    recentSearchCount: Int,
    reviewCount: Int
) {
    Card(
        shape     = CARD_SHAPE,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        // 수정 1: 카드 배경 흰색, 테두리 추가
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        border    = BorderStroke(0.5.dp, CARD_BORDER_COLOR)
    ) {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = CARD_PADDING),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(count = scrapCount,        label = "스크랩")
            StatDivider()
            StatItem(count = recentSearchCount, label = "최근 검색")
            StatDivider()
            StatItem(count = reviewCount,       label = "내 리뷰")
        }
    }
}

@Composable
private fun StatItem(count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text          = count.toString(),
            fontSize      = 22.sp,
            fontWeight    = FontWeight.SemiBold,
            letterSpacing = (-0.44).sp,
            color         = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text  = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
        )
    }
}

@Composable
private fun StatDivider() {
    Box(
        modifier = Modifier
            .height(36.dp)
            .width(1.dp)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
    )
}

// ─── 섹션 카드 컨테이너 ──────────────────────────────────────────────────────

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        shape     = CARD_SHAPE,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        // 수정 1: 카드 배경 흰색, 테두리 추가
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        border    = BorderStroke(0.5.dp, CARD_BORDER_COLOR)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text     = title,
                style    = MaterialTheme.typography.labelMedium,
                // 수정 2: 섹션 타이틀 — 보라 → 회색, SemiBold → Medium, letterSpacing 추가
                fontWeight    = FontWeight.Medium,
                color         = ICON_GRAY,
                letterSpacing = 0.3.sp,
                modifier = Modifier.padding(
                    start  = CARD_PADDING,
                    top    = CARD_PADDING,
                    bottom = 8.dp
                )
            )
            content()
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

// 섹션 내 항목 간 구분선
@Composable
private fun SectionDivider() {
    HorizontalDivider(
        modifier  = Modifier.padding(horizontal = CARD_PADDING),
        thickness = 0.5.dp,
        color     = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}

// ─── 개별 행 컴포저블 ─────────────────────────────────────────────────────────

// 클릭 가능한 활동/설정 행
@Composable
private fun ActivityRow(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    // 수정 3: 로그아웃 등 위험 동작 여부 — true면 아이콘·텍스트 빨간색
    isDestructive: Boolean = false
) {
    val contentColor = if (isDestructive) DESTRUCTIVE else Color.Unspecified

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = CARD_PADDING, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector        = icon,
            contentDescription = null,
            // 수정 3: 아이콘 색상 — 보라 → 회색 (로그아웃만 빨간색 유지)
            tint               = if (isDestructive) DESTRUCTIVE else ICON_GRAY,
            modifier           = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text     = label,
            style    = MaterialTheme.typography.bodyMedium,
            color    = contentColor,
            modifier = Modifier.weight(1f)
        )
        if (!isDestructive) {
            Icon(
                imageVector        = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                modifier           = Modifier.size(18.dp)
            )
        }
    }
}

// 알림 토글 행
@Composable
private fun NotificationRow(
    enabled: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = CARD_PADDING, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector        = Icons.Outlined.Notifications,
            contentDescription = null,
            // 수정 3: 아이콘 색상 — 보라 → 회색
            tint               = ICON_GRAY,
            modifier           = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text     = "알림",
            style    = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked         = enabled,
            onCheckedChange = { onToggle() },
            colors          = SwitchDefaults.colors(
                checkedThumbColor   = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor   = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )
        )
    }
}

// 앱 버전 행 (클릭 없음)
@Composable
private fun VersionRow(version: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = CARD_PADDING, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector        = Icons.Outlined.Info,
            contentDescription = null,
            // 수정 3: 아이콘 색상 — 회색으로 통일
            tint               = ICON_GRAY,
            modifier           = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text     = "앱 버전",
            style    = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text  = version,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
        )
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true, name = "마이페이지 기본")
@Composable
fun MyPageScreenPreview() {
    PopSpotTheme {
        MyPageContent(
            uiState = MyPageUiState(
                scrapCount          = 8,
                recentSearchCount   = 5,
                reviewCount         = 2,
                notificationEnabled = true
            ),
            onToggleNotification = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "마이페이지 알림 OFF")
@Composable
fun MyPageNotificationOffPreview() {
    PopSpotTheme {
        MyPageContent(
            uiState              = MyPageUiState(notificationEnabled = false),
            onToggleNotification = {}
        )
    }
}
