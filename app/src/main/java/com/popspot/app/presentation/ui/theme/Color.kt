package com.popspot.app.presentation.ui.theme

import androidx.compose.ui.graphics.Color

// ════════════════════════════════════════════════════════════════════════════
// PopSpot Design System — Color Tokens
// 레퍼런스 UI 이미지에서 직접 추출한 색상값
// ════════════════════════════════════════════════════════════════════════════

// ── Primary Violet ────────────────────────────────────────────────────────────
/** 배너 그라디언트 시작, 다크 강조 */
val Violet800 = Color(0xFF5B21B6)
/** 메인 Primary — CTA 버튼, 선택된 칩, 배지 배경 */
val Violet600 = Color(0xFF7C3AED)
/** Primary 호버/보조 */
val Violet500 = Color(0xFF8B5CF6)
/** 칩/카드 배경 tint */
val Violet100 = Color(0xFFEDE9FE)
/** 페이지 배경 (연한 보라 흰색) */
val Violet50  = Color(0xFFF5F3FF)

// ── Accent Pink ───────────────────────────────────────────────────────────────
/** 스크랩 버튼, 하트 아이콘, 다시 시도 버튼 */
val Pink500 = Color(0xFFEC4899)
/** Pink 배경 tint */
val Pink100 = Color(0xFFFCE7F3)

// ── Official / Success ────────────────────────────────────────────────────────
/** 공식 데이터 배지, 성공 상태 */
val Emerald500 = Color(0xFF10B981)
/** Emerald 배경 tint */
val Emerald100 = Color(0xFFD1FAE5)

// ── Neutral Text ──────────────────────────────────────────────────────────────
/** 본문 주요 텍스트 */
val Gray900 = Color(0xFF111827)
/** 보조 텍스트 (날짜, 부제목) */
val Gray600 = Color(0xFF6B7280)
/** 비활성 / placeholder */
val Gray400 = Color(0xFF9CA3AF)
/** 구분선, 테두리 */
val Gray200 = Color(0xFFE5E7EB)
/** shimmer base */
val Gray100 = Color(0xFFF3F4F6)

// ── Surface ───────────────────────────────────────────────────────────────────
val White = Color(0xFFFFFFFF)

// ── Status ────────────────────────────────────────────────────────────────────
val ErrorRed    = Color(0xFFEF4444)
val WarningAmber = Color(0xFFF59E0B)

// ── On-color aliases ─────────────────────────────────────────────────────────
val OnPrimary   = White
val OnSecondary = White
val OnTertiary  = White

// ── 기존 이름 호환 유지 (다른 화면에서 참조 중) ─────────────────────────────
val FestivalPurple = Violet600
val AccentPink     = Pink500
val MysticLavender = Violet100
val TealWave       = Emerald100
val SoftIvory      = Violet50
val WhiteSurface   = White
val TextPrimary    = Gray900
val TextSub        = Gray600
