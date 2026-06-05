package com.popspot.app.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.popspot.app.presentation.auth.AuthViewModel
import com.popspot.app.presentation.auth.InterestSelectionScreen
import com.popspot.app.presentation.auth.LoginScreen
import com.popspot.app.presentation.detail.DetailScreen
import com.popspot.app.presentation.home.HomeScreen
import com.popspot.app.presentation.mypage.GuestMyPageScreen
import com.popspot.app.presentation.mypage.MyPageScreen
import com.popspot.app.presentation.nearby.NearbyScreen // 신규 "내 주변" 화면
import com.popspot.app.presentation.popup.PopupFeedScreen
import com.popspot.app.presentation.scrap.ScrapScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar           = { BottomNavBar(navController = navController) },
        contentWindowInsets = WindowInsets(0)
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

            // "공식 행사" 라우트 제거 → "내 주변" 라우트 추가
            composable(BottomNavItem.Nearby.route) {
                NearbyScreen()
            }

            composable(BottomNavItem.Scrap.route) {
                ScrapScreen(navController = navController)
            }

            // 로그인 상태에 따라 MyPage / Login / InterestSelection 중 하나 표시
            composable(BottomNavItem.MyPage.route) {
                val authViewModel: AuthViewModel = hiltViewModel()
                val authUiState by authViewModel.uiState.collectAsState()
                var isGuest by remember { mutableStateOf(false) }

                when {
                    isGuest ->
                        GuestMyPageScreen(onLoginClick = { isGuest = false })

                    authUiState.user == null ->
                        LoginScreen(
                            viewModel    = authViewModel,
                            onGuestClick = { isGuest = true }
                        )

                    authUiState.userTags.isEmpty() ->
                        InterestSelectionScreen(
                            onComplete = { selectedTags ->
                                authViewModel.saveTags(selectedTags)
                            }
                        )

                    else ->
                        MyPageScreen(viewModel = authViewModel)
                }
            }

            // SavedStateHandle["id"] 가 hiltViewModel()에서 자동 주입됨
            composable(
                route     = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                DetailScreen(navController = navController)
            }
        }
    }
}
