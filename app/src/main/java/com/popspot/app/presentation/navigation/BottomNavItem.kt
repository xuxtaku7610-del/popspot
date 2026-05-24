package com.popspot.app.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home         : BottomNavItem("home",           Icons.Outlined.Home,      "홈")
    object PopupTrend   : BottomNavItem("popup_trend",    Icons.Outlined.Storefront, "팝업 트렌드")
    object OfficialEvent: BottomNavItem("official_event", Icons.Outlined.Event,     "공식 행사")
    object Scrap        : BottomNavItem("scrap",          Icons.Outlined.Bookmarks, "스크랩")
    object MyPage       : BottomNavItem("mypage",         Icons.Outlined.Person,    "마이페이지")
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

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface  // MysticLavender — light bg
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick  = {
                    navController.navigate(item.route) {
                        // Pop back to start so the back stack doesn't grow unboundedly
                        popUpTo(BottomNavItem.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState    = true
                    }
                },
                icon  = {
                    Icon(
                        imageVector        = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text       = item.label,
                        fontSize   = 10.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    // Purple pill indicator with white icon when selected
                    indicatorColor      = MaterialTheme.colorScheme.primary,
                    selectedIconColor   = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    selectedTextColor   = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            )
        }
    }
}
