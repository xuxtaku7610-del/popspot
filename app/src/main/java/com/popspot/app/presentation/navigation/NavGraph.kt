package com.popspot.app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.popspot.app.presentation.detail.DetailScreen
import com.popspot.app.presentation.event.EventListScreen
import com.popspot.app.presentation.home.HomeScreen
import com.popspot.app.presentation.mypage.MyPageScreen
import com.popspot.app.presentation.popup.PopupFeedScreen
import com.popspot.app.presentation.scrap.ScrapScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = BottomNavItem.Home.route,
            modifier         = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(BottomNavItem.PopupTrend.route) {
                PopupFeedScreen(navController = navController)
            }

            composable(BottomNavItem.OfficialEvent.route) {
                EventListScreen(navController = navController)
            }

            composable(BottomNavItem.Scrap.route) {
                ScrapScreen(navController = navController)
            }

            composable(BottomNavItem.MyPage.route) {
                MyPageScreen(navController = navController)
            }

            // Explicit navArgument declaration ensures SavedStateHandle["id"]
            // is populated correctly when DetailViewModel is injected via hiltViewModel()
            composable(
                route     = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                DetailScreen(navController = navController)
            }
        }
    }
}

