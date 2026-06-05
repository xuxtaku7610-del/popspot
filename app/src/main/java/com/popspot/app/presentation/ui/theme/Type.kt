package com.popspot.app.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ════════════════════════════════════════════════════════════════════════════
// PopSpot Design System — Typography
// 레퍼런스 UI 이미지 텍스트 계층에서 추출
//
// 계층 구조:
//   displayLarge  → 앱 브랜드명 "Pop-Spot"
//   titleLarge    → 배너 제목, 상세 페이지 제목
//   titleMedium   → 스크린 제목 (팝업 트렌드, 공식 행사…)
//   titleSmall    → 섹션 헤더 (오늘의 핫플 🔥)
//   bodyLarge     → 카드 제목 (2줄 클램프)
//   bodyMedium    → 본문, 설명 텍스트
//   bodySmall     → 보조 설명, 날짜
//   labelLarge    → 버튼 텍스트
//   labelMedium   → 배지, 칩 레이블
//   labelSmall    → 캡션, 출처 정보
// ════════════════════════════════════════════════════════════════════════════

val Typography = Typography(

    // Pop-Spot 브랜드명 — TopAppBar 왼쪽
    displayLarge = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.ExtraBold,
        fontSize      = 24.sp,
        lineHeight    = 32.sp,
        letterSpacing = (-0.5).sp
    ),

    // 배너 제목, 상세 화면 메인 제목
    titleLarge = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Bold,
        fontSize      = 20.sp,
        lineHeight    = 28.sp,
        letterSpacing = 0.sp
    ),

    // 스크린 AppBar 제목 (팝업 트렌드, 공식 행사…)
    titleMedium = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Bold,
        fontSize      = 18.sp,
        lineHeight    = 26.sp,
        letterSpacing = 0.15.sp
    ),

    // 섹션 헤더 (오늘의 핫플 🔥, 실시간 팝업 트렌드…)
    titleSmall = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Bold,
        fontSize      = 15.sp,
        lineHeight    = 22.sp,
        letterSpacing = 0.1.sp
    ),

    // 카드 제목 (2줄 클램프, 굵게)
    bodyLarge = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.SemiBold,
        fontSize      = 14.sp,
        lineHeight    = 21.sp,
        letterSpacing = 0.sp
    ),

    // 본문 텍스트 (설명, 미리보기)
    bodyMedium = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Normal,
        fontSize      = 13.sp,
        lineHeight    = 19.sp,
        letterSpacing = 0.25.sp
    ),

    // 보조 설명, 날짜, 위치
    bodySmall = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Normal,
        fontSize      = 12.sp,
        lineHeight    = 17.sp,
        letterSpacing = 0.4.sp
    ),

    // 버튼, 세그먼트 탭 텍스트
    labelLarge = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.SemiBold,
        fontSize      = 14.sp,
        lineHeight    = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // 배지, 칩 레이블
    labelMedium = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Medium,
        fontSize      = 12.sp,
        lineHeight    = 16.sp,
        letterSpacing = 0.5.sp
    ),

    // 캡션, 출처, 작은 메타 정보
    labelSmall = TextStyle(
        fontFamily    = FontFamily.Default,
        fontWeight    = FontWeight.Medium,
        fontSize      = 11.sp,
        lineHeight    = 16.sp,
        letterSpacing = 0.5.sp
    )
)
