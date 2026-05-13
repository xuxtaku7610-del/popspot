package com.popspot.app.presentation.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val SHIMMER_CARD_COUNT = 3

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier              = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment   = Alignment.CenterHorizontally,
        verticalArrangement   = Arrangement.Top
    ) {
        // Progress indicator — uses FestivalPurple (primary token)
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
            color    = MaterialTheme.colorScheme.primary,
            strokeWidth = 3.dp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text  = "잠시만 기다려주세요...",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Shimmer placeholder cards — one shared transition for all cards
        repeat(SHIMMER_CARD_COUNT) { index ->
            ShimmerCard(
                // Stagger the alpha range per card so they pulse at slightly different phases
                baseAlpha = 0.15f + index * 0.03f
            )
            if (index < SHIMMER_CARD_COUNT - 1) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

// ─── Shimmer card ─────────────────────────────────────────────────────────────

@Composable
private fun ShimmerCard(baseAlpha: Float = 0.18f) {
    val transition = rememberInfiniteTransition(label = "shimmer_$baseAlpha")
    val alpha by transition.animateFloat(
        initialValue = baseAlpha,
        targetValue  = baseAlpha + 0.30f,
        animationSpec = infiniteRepeatable(
            animation  = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp),
        shape  = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            // Animate the alpha of the surface-tone gray so it pulses like a real shimmer
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha)
        )
    ) {}
}
