# PopSpot UI 개선 프롬프트
> Android (Jetpack Compose) 기반 — 상용화 수준 리디자인용

---

## 공통 디자인 시스템

```
You are a senior mobile UI/UX designer. Redesign this Android Jetpack Compose app to production-ready quality.
Apply the following design system consistently across ALL screens:

BRAND COLOR: Primary #7B5CF5 (purple), Secondary #A78BFA

TYPOGRAPHY SCALE (Korean + Latin, Pretendard font):
- Display: 24sp / weight 700 / lineHeight 1.3
- H1: 22sp / weight 700 / lineHeight 1.35
- H2: 18sp / weight 600 / lineHeight 1.4
- H3: 16sp / weight 600 / lineHeight 1.45
- Body: 15sp / weight 400 / lineHeight 1.6
- Body small: 13sp / weight 400 / lineHeight 1.6
- Caption: 12sp / weight 400 / lineHeight 1.5
- Label/Tag: 11sp / weight 500 / lineHeight 1.4

SPACING SYSTEM (8dp grid):
- xs: 4dp / sm: 8dp / md: 16dp / lg: 24dp / xl: 32dp / 2xl: 48dp
All padding, margin, and gap values MUST be multiples of 8dp.

TOUCH TARGETS:
- Minimum tap area: 44×44dp for ALL interactive elements
- Bottom tab bar height: 64dp (icon 24dp + label 11sp + padding)
- Buttons minimum height: 48dp
- Input fields minimum height: 52dp

SHAPE (border radius):
- Buttons, inputs: 12dp
- Cards: 16dp
- Chips/Tags: 99dp (fully rounded)
- Bottom sheet: 20dp top corners only

ELEVATION / SHADOWS:
- Card: 0 2dp 12dp rgba(0,0,0,0.06)
- Modal/Sheet: 0 -4dp 24dp rgba(0,0,0,0.10)
- Flat elements: no shadow

EMPTY STATE DESIGN:
- Always include: illustration (80×80dp icon) + primary message (H2) + secondary message (Body, max 2 lines) + action button
- NEVER show raw HTTP error codes (4xx/5xx) to users
- Use friendly, human language in Korean
```

---

## 1. 홈 화면 (메인)

```
Redesign the PopSpot home screen with these specifications:

HEADER:
- App name "PopSpot" left, 20sp/700
- City selector with dropdown arrow: center, 15sp/500, rounded pill background
- Notification bell icon right: 24dp, with red dot badge (8dp) for unread
- Header height: 56dp, background white with bottom border 0.5dp

HERO BANNER (오늘의 핫플):
- Card height: 160dp
- Purple gradient: #7B5CF5 → #A78BFA (left to right)
- Title text: 20sp/700 white, "팝업스토어 탐색하기"
- Subtitle: 13sp white opacity 0.85, one line max
- CTA button: white background, purple text, 36dp height, 12dp radius, "자세히 보기 →"
- Section label "오늘의 핫플 🔥" above: 16sp/600 + "더보기 >" right-aligned in 13sp primary purple

SECTION HEADERS:
- Left: emoji + title 16sp/600
- Right: "더보기 >" 13sp #7B5CF5
- Margin top: 32dp from previous section

BOTTOM TAB BAR:
- Height: 64dp
- Background: white with top border 0.5dp #E5E7EB
- 5 tabs: 홈 / 팝업트렌드 / 공식행사 / 스크랩 / 마이페이지
- Active: filled icon + label in #7B5CF5
- Inactive: outline icon + label in #9CA3AF
- Icon size: 24dp, label: 11sp/500
- Active tab: small purple dot indicator above icon (4dp circle)
```

---

## 2. 팝업 트렌드 화면

```
Redesign the PopSpot popup trend screen:

TOP APP BAR:
- Back arrow left (24dp), "팝업 트렌드" center 18sp/600, search icon right
- Height: 56dp

SEARCH BAR:
- Full width, height 48dp, border-radius 12dp
- Background: #F3F4F6, border: none
- Placeholder: "검색어를 입력하세요" 14sp gray
- Left search icon: 18dp #9CA3AF
- Margin: 16dp horizontal, 12dp top

FILTER CHIPS:
- Horizontal scroll, no scrollbar visible
- Chip height: 34dp, padding: 0 16dp
- Active chip: #7B5CF5 background, white text 13sp/500
- Inactive chip: white background, #E5E7EB border, #374151 text 13sp/400
- Gap between chips: 8dp

EMPTY / ERROR STATE:
- Center vertically in remaining space
- Icon: 64dp, #D1D5DB
- Primary message: "잠시 연결이 끊겼어요" — 17sp/600 #111827
- Secondary: "네트워크를 확인하고 다시 시도해 주세요" — 14sp #6B7280
- NEVER display "HTTP 401" — map auth errors to "로그인이 필요해요"
- Retry button: "다시 시도" — height 48dp, outlined style, #7B5CF5
```

---

## 3. 공식 행사 화면

```
Redesign the official events (공식 행사) screen:

TOP BAR: Same pattern as trend screen — back + title + search

FILTER ROW:
- Location pill: "서울 ▾" — height 36dp, border 1dp #E5E7EB, 99dp radius
- Date pill: calendar icon + "날짜 선택 ▾" — same style
- Filter button: "전체 필터" with sliders icon, same height
- All: 13sp/500 text
- Row padding: 16dp horizontal, 12dp vertical

EVENT CARD:
- Full width card, 16dp radius, white background, subtle shadow
- Thumbnail image: 100% width, 160dp height, object-fit cover, top-radius 16dp
- Content padding: 16dp
- Category tag: colored pill 11sp ("공식행사" in purple)
- Event title: 16sp/600, max 2 lines
- Date/location row: 13sp gray with calendar and pin icons

EMPTY STATE:
- 80dp icon in #E9D5FF circle background
- "조건에 맞는 행사가 없어요" — 17sp/600
- "다른 키워드로 검색하거나 필터를 변경해 보세요." — 14sp gray
- "필터 초기화" button — outlined, 48dp height
```

---

## 4. 스크랩 화면

```
Redesign the scrap (스크랩) screen:

HEADER:
- "스크랩" title centered 18sp/600
- "편집" text button right — 15sp #7B5CF5, 8dp padding

CATEGORY TABS (pill style):
- "전체" / "팝업" / "공식 행사"
- Selected: #7B5CF5 background, white text 13sp/500, 34dp height
- Unselected: #F3F4F6 background, #6B7280 text
- Margin: 16dp top from header, 24dp bottom

EMPTY STATE:
- "스크랩한 항목이 없어요" — 17sp/600
- "마음에 드는 팝업과 행사를 저장해 보세요" — 14sp gray
- "탐색하러 가기 →" CTA button — filled purple, 48dp, full-width minus 32dp margin
```

---

## 개발자 핵심 체크리스트

```
Before shipping ANY screen, verify:

[ ] All tap targets are minimum 44×44dp
[ ] No raw error codes (HTTP 4xx/5xx) shown to users
[ ] Empty states have: icon + message + action button
[ ] Font sizes: never below 11sp, body text minimum 13sp
[ ] All spacing is a multiple of 4dp (prefer 8dp multiples)
[ ] Korean text uses Pretendard font family
[ ] Text contrast ratio meets WCAG AA (4.5:1 for body, 3:1 for large text)
[ ] Loading states exist for all async operations
[ ] Error states exist for all network calls
[ ] Bottom tab bar height minimum 64dp
[ ] Card border-radius minimum 12dp (16dp preferred)
[ ] Section headers have consistent typography (16sp/600)
```
