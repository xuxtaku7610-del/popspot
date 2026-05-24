package com.popspot.app.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.popspot.app.presentation.auth.AuthViewModel

@Composable
fun MyPageScreen(viewModel: AuthViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val userName = uiState.user?.displayName ?: "사용자"
    val userEmail = uiState.user?.email ?: "user@popspot.com"
    val userTags = uiState.userTags // 💡 뷰모델에서 불러온 진짜 태그 데이터

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "마이페이지",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        MyPageProfileCard(userName = userName, userEmail = userEmail, userTags = userTags)
        StatsCard()

        MenuSection(title = "내 활동") {
            MenuItem(title = "스크랩 목록")
            MenuItem(title = "최근 검색 기록")
            MenuItem(title = "내 리뷰")
        }

        MenuSection(title = "앱 설정") {
            MenuItem(title = "기본 지도 타입")
            MenuItem(title = "알림", hasSwitch = true)
            MenuItem(title = "다크 모드")
        }

        MenuSection(title = "앱 정보") {
            MenuItem(title = "공지사항")
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            onClick = { viewModel.logout() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "로그아웃", color = Color.Red, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun MyPageProfileCard(userName: String, userEmail: String, userTags: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(72.dp).clip(CircleShape).background(Color(0xFF6200EE)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.take(1),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = userName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "편집", fontSize = 12.sp, color = Color(0xFF6200EE))
                }
                Text(text = userEmail, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(vertical = 4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    userTags.forEach { tag ->
                        Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFF4F0FF)) {
                            Text(text = "#$tag", fontSize = 12.sp, color = Color(0xFF6200EE), modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(count = "0", label = "스크랩")
            Divider(modifier = Modifier.height(40.dp).width(1.dp), color = Color.LightGray)
            StatItem(count = "0", label = "최근 검색")
            Divider(modifier = Modifier.height(40.dp).width(1.dp), color = Color.LightGray)
            StatItem(count = "0", label = "내 리뷰")
        }
    }
}

@Composable
fun StatItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun MenuSection(title: String, content: @Composable () -> Unit) {
    Text(
        text = title,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp)
    )
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column { content() }
    }
}

@Composable
fun MenuItem(title: String, hasSwitch: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 16.sp)
        if (hasSwitch) {
            Switch(checked = true, onCheckedChange = { })
        } else {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "이동", tint = Color.LightGray)
        }
    }
}