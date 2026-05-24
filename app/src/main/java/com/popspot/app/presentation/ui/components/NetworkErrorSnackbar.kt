package com.popspot.app.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WifiOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Toast-style overlay card used to surface transient network or data errors.
 *
 * Usage:
 *   NetworkErrorSnackbar(visible = hasError, message = "네트워크 연결이 불안정해요")
 *   NetworkErrorSnackbar(visible = hasFileError, message = "파일을 불러오지 못했어요",
 *       icon = Icons.Outlined.InsertDriveFile)
 *
 * Place this at the bottom of a Box/Scaffold so it overlays existing content.
 */
@Composable
fun NetworkErrorSnackbar(
    visible: Boolean,
    message: String,
    icon: ImageVector = Icons.Outlined.WifiOff,   // default: wifi-off for network errors
    containerColor: Color = MaterialTheme.colorScheme.errorContainer,
    contentColor: Color   = MaterialTheme.colorScheme.onErrorContainer,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter   = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit    = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape     = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors    = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Row(
                modifier          = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector        = icon,
                    contentDescription = null,
                    tint               = contentColor,
                    modifier           = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text       = message,
                    style      = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color      = contentColor
                )
            }
        }
    }
}
