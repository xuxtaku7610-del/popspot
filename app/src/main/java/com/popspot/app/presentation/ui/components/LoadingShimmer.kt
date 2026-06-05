package com.popspot.app.presentation.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.popspot.app.presentation.ui.theme.PopSpotTheme

// ════════════════════════════════════════════════════════════════════════════
// LoadingShimmer
// 콘텐츠 로딩 중 표시하는 스켈레톤 shimmer 애니메이션.
// 레퍼런스 이미지 6번 — Loading 상태 레이아웃 재현
// ════════════════════════════════════════════════════════════════════════════

/**
 * Shimmer 브러시를 생성하는 유틸 컴포저블.
 * 좌상단 → 우하단 대각선 방향으로 흐르는 그라디언트.
 *
 * @return 무한 반복 애니메이션이 적용된 [Brush]
 */
@Composable
fun shimmerBrush(): Brush {
    val shimmerColors = listOf(
        ShimmerDefaults.BaseColor,
        ShimmerDefaults.HighlightColor,
        ShimmerDefaults.BaseColor
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue  = 0f,
        targetValue   = ShimmerDefaults.AnimationTarget,
        animationSpec = infiniteRepeatable(
            animation  = tween(
                durationMillis = ShimmerDefaults.DurationMs,
                easing         = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start  = Offset.Zero,
        end    = Offset(x = translateAnim, y = translateAnim)
    )
}

/**
 * Shimmer 효과가 적용된 기본 사각형 박스.
 *
 * @param modifier 너비/높이/모서리 등 외부에서 지정
 */
@Composable
fun ShimmerBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(shimmerBrush()))
}

/**
 * 피드(PopupFeedScreen) 스타일 스켈레톤 로딩 전체 레이아웃.
 * 검색창 → 칩 행 → 카드 리스트 순서로 구성.
 */
@Composable
fun FeedLoadingContent(modifier: Modifier = Modifier) {
    val brush = shimmerBrush()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = ShimmerDefaults.HorizontalPadding)
    ) {
        // 검색창 플레이스홀더
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ShimmerDefaults.SearchBarHeight)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(50.dp))
                .background(brush)
        )

        // 칩 행 플레이스홀더
        Spacer(Modifier.height(12.dp))
        LazyRow(
            contentPadding        = PaddingValues(horizontal = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) {
                Box(
                    modifier = Modifier
                        .width(ShimmerDefaults.ChipWidth)
                        .height(ShimmerDefaults.ChipHeight)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(50.dp))
                        .background(brush)
                )
            }
        }

        // 카드 리스트 플레이스홀더
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(4) {
                ShimmerCardItem(brush = brush)
            }
        }
    }
}

/**
 * 단일 피드 카드 스켈레톤.
 * 썸네일(60×60) + 텍스트 라인 3개 구조.
 */
@Composable
fun ShimmerCardItem(
    modifier: Modifier = Modifier,
    brush: Brush = shimmerBrush()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 썸네일 플레이스홀더
        Box(
            modifier = Modifier
                .size(ShimmerDefaults.ThumbnailSize)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                .background(brush)
        )

        // 텍스트 라인 플레이스홀더
        Column(
            modifier            = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 배지 자리
            Box(
                modifier = Modifier
                    .width(ShimmerDefaults.BadgeWidth)
                    .height(ShimmerDefaults.BadgeHeight)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                    .background(brush)
            )
            // 제목 1행
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ShimmerDefaults.LineHeightTitle)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                    .background(brush)
            )
            // 제목 2행 (짧게)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(ShimmerDefaults.LineHeightTitle)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                    .background(brush)
            )
            // 날짜 라인
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(ShimmerDefaults.LineHeightCaption)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }
    }
}

/** LoadingShimmer 상수 */
object ShimmerDefaults {
    /** Shimmer 기본 색상 */
    val BaseColor      = Color(0xFFE2E2E2)
    /** Shimmer 하이라이트 (밝은 부분) */
    val HighlightColor = Color(0xFFF5F5F5)
    /** 애니메이션 이동 거리 */
    const val AnimationTarget = 1000f
    /** 1사이클 시간 (ms) */
    const val DurationMs      = 1200

    val HorizontalPadding: Dp = 20.dp
    val SearchBarHeight: Dp   = 48.dp
    val ChipWidth: Dp         = 70.dp
    val ChipHeight: Dp        = 36.dp
    val ThumbnailSize: Dp     = 60.dp
    val BadgeWidth: Dp        = 80.dp
    val BadgeHeight: Dp       = 16.dp
    val LineHeightTitle: Dp   = 14.dp
    val LineHeightCaption: Dp = 11.dp
}

// ── Previews ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFF5F3FF)
@Composable
private fun PreviewFeedLoading() {
    PopSpotTheme {
        FeedLoadingContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewShimmerCard() {
    PopSpotTheme {
        ShimmerCardItem(modifier = Modifier.padding(16.dp))
    }
}
