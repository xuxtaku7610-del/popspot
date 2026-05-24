package com.popspot.app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.popspot.app.presentation.detail.DetailScreen
import com.popspot.app.presentation.event.EventListScreen
import com.popspot.app.presentation.home.HomeScreen
import com.popspot.app.presentation.popup.PopupFeedScreen
import com.popspot.app.presentation.scrap.ScrapScreen
import com.popspot.app.presentation.auth.AuthViewModel
import com.popspot.app.presentation.auth.LoginScreen
import com.popspot.app.presentation.auth.InterestSelectionScreen
import com.popspot.app.presentation.mypage.MyPageScreen // 💡 진짜 마이페이지 Import!

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

            // 💡 여기서 마법이 일어남! 상태에 따라 알아서 화면이 슥슥 바뀜
            composable(BottomNavItem.MyPage.route) {
                val authViewModel: AuthViewModel = hiltViewModel()
                val authUiState by authViewModel.uiState.collectAsState()

                if (authUiState.user == null) {

                    LoginScreen(viewModel = authViewModel)
                } else if (authUiState.userTags.isEmpty()) {

                    InterestSelectionScreen(
                        onComplete = { selectedTags ->
                            authViewModel.saveTags(selectedTags)
                        }
                    )
                } else {

                    MyPageScreen(viewModel = authViewModel)
                }
            }

            composable(
                route     = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                DetailScreen(navController = navController)
            }
        }
    }
}