package com.popspot.app.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home          : BottomNavItem("home",           Icons.Outlined.Home,       "홈")
    object PopupTrend    : BottomNavItem("popup_trend",    Icons.Outlined.Storefront,  "팝업 트렌드")
    object OfficialEvent : BottomNavItem("official_event", Icons.Outlined.Event,      "공식 행사")
    object Scrap         : BottomNavItem("scrap",          Icons.Outlined.Bookmarks,  "스크랩")
    object MyPage        : BottomNavItem("mypage",         Icons.Outlined.Person,     "마이페이지")
}

// ─── Bottom navigation bar ────────────────────────────────────────────────────

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.PopupTrend,
        BottomNavItem.OfficialEvent,
        BottomNavItem.Scrap,
        BottomNavItem.MyPage
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column {
        // 수정 4: 탭바 상단 구분선 (0.5dp, #E5E7EB)
        HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFE5E7EB))

        NavigationBar(
            // 수정 4: 탭바 배경을 보라색 → 흰색으로 변경
            containerColor = Color.White.copy(alpha = 0.92f)
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route

                NavigationBarItem(
                    selected = selected,
                    onClick  = {
                        navController.navigate(item.route) {
                            popUpTo(BottomNavItem.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState    = true
                        }
                    },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // 수정 4: 활성 탭 → 아이콘 위 4dp 보라 dot (비활성 시 투명으로 높이 유지)
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(
                                        color = if (selected) MaterialTheme.colorScheme.primary
                                                else Color.Transparent,
                                        shape = CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Icon(
                                imageVector        = item.icon,
                                contentDescription = item.label
                            )
                        }
                    },
                    label = {
                        Text(
                            text       = item.label,
                            fontSize   = 10.sp,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        // 수정 4: 기존 보라 박스 indicator 제거 → dot으로 대체
                        indicatorColor      = Color.Transparent,
                        selectedIconColor   = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                        selectedTextColor   = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                    )
                )
            }
        }
    }
}
