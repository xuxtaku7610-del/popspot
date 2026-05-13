package com.popspot.app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyScreen(
    message: String = "검색 결과가 없어요 😢",
    description: String = "다른 키워드로 검색해보거나 필터를 변경해보세요.",
    onReset: (() -> Unit)? = null,        // null = hide reset button
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // ── Magnifying-glass illustration (Icon layered in a circle) ──────
        Box(
            modifier         = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            // Outer soft circle — faint primary tint
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
            )
            // Inner circle — slightly stronger tint for depth
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.13f))
            )
            // Search-off icon
            Icon(
                imageVector        = Icons.Outlined.SearchOff,
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.primary,
                modifier           = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Title ─────────────────────────────────────────────────────────
        Text(
            text       = message,
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize   = 18.sp,
            textAlign  = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ── Description ───────────────────────────────────────────────────
        Text(
            text      = description,
            style     = MaterialTheme.typography.bodySmall,
            color     = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
            textAlign = TextAlign.Center
        )

        // ── Reset button (only when a callback is provided) ───────────────
        if (onReset != null) {
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = onReset,
                shape   = RoundedCornerShape(10.dp),
                colors  = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text       = "검색 초기화",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
