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
import androidx.compose.material.icons.outlined.WifiOff
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
fun ErrorScreen(
    message: String = "",                  // optional detail shown beneath the fixed description
    onRetry: (() -> Unit)? = null,         // null = hide retry button
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // ── Warning illustration (Icon layered in a circle) ───────────────
        Box(
            modifier         = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            // Outer soft circle — faint error tint
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.08f))
            )
            // Inner circle — slightly stronger tint for depth
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.14f))
            )
            // Wifi-off icon (signals network error specifically)
            Icon(
                imageVector        = Icons.Outlined.WifiOff,
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.error,
                modifier           = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Fixed title ───────────────────────────────────────────────────
        Text(
            text       = "데이터를 불러오지 못했어요",
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize   = 18.sp,
            textAlign  = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ── Fixed description ──────────────────────────────────────────────
        Text(
            text      = "네트워크 연결을 확인한 후 다시 시도해주세요.",
            style     = MaterialTheme.typography.bodySmall,
            color     = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
            textAlign = TextAlign.Center
        )

        // ── Optional detail from caller (e.g. specific error message) ─────
        if (message.isNotBlank()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text      = message,
                style     = MaterialTheme.typography.labelSmall,
                color     = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                textAlign = TextAlign.Center
            )
        }

        // ── Retry button (only when a callback is provided) ───────────────
        if (onRetry != null) {
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = onRetry,
                shape   = RoundedCornerShape(10.dp),
                colors  = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text       = "다시 시도",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
