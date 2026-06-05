package com.popspot.app.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ════════════════════════════════════════════════════════════════════════════
// PopSpot Design System — Shape Tokens
// 레퍼런스 UI 이미지에서 컴포넌트별 모서리 반경 추출
// ════════════════════════════════════════════════════════════════════════════

/**
 * 컴포넌트별 모서리 반경 상수.
 * 직접 참조할 때는 [PopSpotShapes] 대신 이 object를 사용.
 */
object PopSpotRadius {
    /** 배지 (NAVER BLOG, 공식 데이터) */
    val Badge = 4.dp
    /** 칩, 태그 */
    val Chip = 8.dp
    /** 칩 pill 형태 (검색창, 해시태그 필터) */
    val ChipPill = 50.dp
    /** 썸네일, 소형 카드 이미지 */
    val Thumbnail = 10.dp
    /** 일반 카드 (피드 아이템, 스크랩 항목) */
    val Card = 16.dp
    /** 히어로 배너, 대형 카드 */
    val Banner = 20.dp
    /** 버튼 */
    val Button = 12.dp
    /** 세그먼트 컨트롤 외곽 */
    val SegmentOuter = 14.dp
    /** 세그먼트 탭 내부 선택 pill */
    val SegmentInner = 10.dp
    /** 바텀 시트, 모달 상단 */
    val BottomSheet = 24.dp
}

/**
 * MaterialTheme.shapes 에 주입되는 Shape 시스템.
 *
 * | Slot        | 사용처                         |
 * |-------------|-------------------------------|
 * | extraSmall  | 배지, 레이블                   |
 * | small       | 칩, 태그                       |
 * | medium      | 일반 카드                      |
 * | large       | 히어로 배너                    |
 * | extraLarge  | 바텀 시트, 전체 팝업           |
 */
val PopSpotShapes = Shapes(
    extraSmall = RoundedCornerShape(PopSpotRadius.Badge),
    small      = RoundedCornerShape(PopSpotRadius.Chip),
    medium     = RoundedCornerShape(PopSpotRadius.Card),
    large      = RoundedCornerShape(PopSpotRadius.Banner),
    extraLarge = RoundedCornerShape(PopSpotRadius.BottomSheet)
)
