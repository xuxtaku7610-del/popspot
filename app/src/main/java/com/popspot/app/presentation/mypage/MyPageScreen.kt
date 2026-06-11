package com.popspot.app.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.popspot.app.presentation.auth.AuthViewModel
import com.popspot.app.presentation.auth.LoginScreen

@Composable
fun MyPageScreen(viewModel: AuthViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val user = uiState.user
    if (user == null) {
        LoginScreen(
            viewModel = viewModel,
            onGuestClick = {
                // 비회원으로 둘러보기 누르면 게스트 마이페이지를 보여주고 싶을 때 사용
                // 지금은 비워둬도 됨
            }
        )
        return
    }

    val userName = user.displayName ?: "사용자"
    val userEmail = user.email ?: "user@popspot.com"
    val userTags = uiState.userTags

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "마이페이지",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )

        MyPageProfileCard(
            userName = userName,
            userEmail = userEmail,
            userTags = userTags
        )

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
            Text(
                text = "로그아웃",
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
@Composable
private fun MyPageProfileCard(userName: String, userEmail: String, userTags: List<String>) {
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
                modifier = Modifier.size(72.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary),
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
                    Text(text = "편집", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                }
                Text(text = userEmail, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(vertical = 4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    userTags.forEach { tag ->
                        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                            Text(text = "#$tag", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatsCard() {
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
            VerticalDivider(modifier = Modifier.height(40.dp), color = Color.LightGray)
            StatItem(count = "0", label = "최근 검색")
            VerticalDivider(modifier = Modifier.height(40.dp), color = Color.LightGray)
            StatItem(count = "0", label = "내 리뷰")
        }
    }
}

@Composable
private fun StatItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
private fun MenuSection(title: String, content: @Composable () -> Unit) {
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
private fun MenuItem(title: String, hasSwitch: Boolean = false) {
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
            Switch(checked = true, onCheckedChange = { /* TODO: 알림 설정 ViewModel 연동 필요 */ })
        } else {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "이동", tint = Color.LightGray)
        }
    }
}

// ─── Guest mode ───────────────────────────────────────────────────────────────

@Composable
fun GuestMyPageScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text       = "마이페이지",
            fontSize   = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier   = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            textAlign  = TextAlign.Center
        )

        GuestProfileCard(onLoginClick = onLoginClick)
        GuestStatsCard()

        MenuSection(title = "내 활동") {
            LockedMenuItem(title = "스크랩 목록")
            LockedMenuItem(title = "최근 검색 기록")
            LockedMenuItem(title = "내 리뷰")
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

        // Login CTA card
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape    = RoundedCornerShape(16.dp),
            colors   = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(
                modifier            = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text       = "로그인하면 모든 기능을 사용할 수 있어요",
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = MaterialTheme.colorScheme.primary,
                    textAlign  = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onLoginClick,
                    colors  = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "로그인하러 가기", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun GuestProfileCard(onLoginClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier          = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier         = Modifier.size(72.dp).clip(CircleShape).background(Color(0xFFBDBDBD)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "👤", fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "게스트", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text     = "로그인이 필요한 서비스입니다",
                    fontSize = 13.sp,
                    color    = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick        = onLoginClick,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    shape          = RoundedCornerShape(8.dp),
                    colors         = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "로그인", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun GuestStatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier              = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GuestStatItem(label = "스크랩")
            VerticalDivider(modifier = Modifier.height(40.dp), color = Color.LightGray)
            GuestStatItem(label = "최근 검색")
            VerticalDivider(modifier = Modifier.height(40.dp), color = Color.LightGray)
            GuestStatItem(label = "내 리뷰")
        }
    }
}

@Composable
private fun GuestStatItem(label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "-", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
private fun LockedMenuItem(title: String) {
    val context = LocalContext.current
    Row(
        modifier              = Modifier
            .fillMaxWidth()
            .clickable {
                Toast.makeText(context, "로그인 후 이용할 수 있어요", Toast.LENGTH_SHORT).show()
            }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 16.sp, color = Color.Gray)
        Icon(
            imageVector        = Icons.Default.Lock,
            contentDescription = "잠금",
            tint               = Color.LightGray
        )
    }
}