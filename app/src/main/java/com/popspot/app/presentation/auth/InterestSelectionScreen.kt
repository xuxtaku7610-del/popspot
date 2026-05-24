package com.popspot.app.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestSelectionScreen(
    onComplete: (List<String>) -> Unit
) {
    val availableTags = listOf("뷰티", "캐릭터", "한정판", "패션", "푸드", "게임/PC", "아이돌", "스포츠")
    val selectedTags = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "관심 있는 팝업스토어\n분야를 선택해 주세요!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "선택한 태그를 기반으로 맞춤 정보를 추천해 드려요.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            availableTags.forEach { tag ->
                val isSelected = selectedTags.contains(tag)
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        if (isSelected) selectedTags.remove(tag)
                        else selectedTags.add(tag)
                    },
                    label = { Text(text = "#$tag") },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color(0xFFF3F4F6),
                        selectedContainerColor = Color(0xFFEBE5FC),
                        selectedLabelColor = Color(0xFF6200EE)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    border = null
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { onComplete(selectedTags.toList()) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            enabled = selectedTags.isNotEmpty()
        ) {
            Text("완료하고 시작하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}