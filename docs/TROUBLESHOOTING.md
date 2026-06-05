# PopSpot — 트러블슈팅 기록

> 개발 세션 중 발생한 이슈와 해결 과정을 기록합니다.  
> 날짜: 2026-06-04

---

## 목차

1. [TopBar가 화면에 표시되지 않는 문제](#1-topbar가-화면에-표시되지-않는-문제)
2. [Coil 이미지 로딩 의존성 미설정](#2-coil-이미지-로딩-의존성-미설정)
3. [HeroBannerCarousel이 표시되지 않는 문제](#3-herobannercaousel이-표시되지-않는-문제)
4. [Naver Blog Search API HTTP 401 오류](#4-naver-blog-search-api-http-401-오류)
5. [NearbyScreen 레이아웃 깨짐 (StatusBar 침범 / BottomNavBar 소실)](#5-nearbyscreen-레이아웃-깨짐)

---

## 1. TopBar가 화면에 표시되지 않는 문제

### 영향 화면
`HomeScreen`, `PopupFeedScreen`

### 증상
- `TopBar.kt`에 `HomeTopBar` / `PopupFeedTopBar`를 신규 구현했지만 화면에 반영되지 않음.

### 원인
`HomeScreen.kt`와 `PopupFeedScreen.kt` 내부에 **동일한 이름의 private 함수**가 이미 정의되어 있었음.

```kotlin
// HomeScreen.kt 내부에 존재하던 로컬 함수 (파라미터 없음)
@Composable
private fun HomeTopBar() { ... }

// PopupFeedScreen.kt 내부에 존재하던 로컬 함수 (파라미터명 불일치)
@Composable
private fun PopupFeedTopBar(onBack: () -> Unit, ...) { ... }
```

Kotlin의 이름 해석 규칙에 의해 로컬 private 함수가 import된 외부 컴포저블보다 우선 호출되어, `TopBar.kt`의 컴포저블이 전혀 실행되지 않았음.

### 해결
1. 각 파일 내부의 private TopBar 함수 **삭제**
2. `TopBar.kt`의 컴포저블 **import 추가**
3. 호출부 파라미터명을 새 시그니처에 맞게 수정

```kotlin
// HomeScreen.kt
import com.popspot.app.presentation.ui.components.HomeTopBar

Scaffold(
    topBar = {
        HomeTopBar(
            cityName            = "서울",
            onCityClick         = { },
            onNotificationClick = { }
        )
    }
)

// PopupFeedScreen.kt
import com.popspot.app.presentation.ui.components.PopupFeedTopBar

Scaffold(
    topBar = {
        PopupFeedTopBar(
            title         = "팝업 트렌드",
            onBackClick   = { navController.popBackStack() },
            onSearchClick = { searchFocusRequester.requestFocus() }
        )
    }
)
```

---

## 2. Coil 이미지 로딩 의존성 미설정

### 영향 화면
`HomeScreen` — 오늘의 핫플 카드

### 증상
- 팝업 카드 썸네일 영역에 이모지만 표시되고 실제 이미지가 로딩되지 않음.

### 원인
`coil-compose` 라이브러리가 프로젝트에 추가되어 있지 않았음.

### 해결

**`gradle/libs.versions.toml`**
```toml
[versions]
coil = "2.6.0"

[libraries]
coil-compose = { group = "io.coil-kt", name = "coil-compose", version.ref = "coil" }
```

**`app/build.gradle.kts`**
```kotlin
implementation(libs.coil.compose)
```

**`HotPlaceCard` 수정** — `imageUrl` 유무에 따라 분기 처리:
```kotlin
if (store.imageUrl.isNotBlank()) {
    SubcomposeAsyncImage(
        model        = store.imageUrl,
        contentScale = ContentScale.Crop,
        loading      = { ShimmerBox(modifier = Modifier.fillMaxSize()) },
        error        = { /* 이모지 폴백 */ }
    )
} else {
    // 기존 이모지 플레이스홀더 유지
}
```

---

## 3. HeroBannerCarousel이 표시되지 않는 문제

### 영향 화면
`HomeScreen`

### 증상
- 배너가 보이지 않거나 "오늘의 핫플 🔥" 섹션 헤더 아래에 카드 목록만 표시됨.

### 원인 1 — 섹션 순서 오류
`HomeContent` 내 컴포저블 배치 순서가 디자인과 반대였음.

| 상태 | 순서 |
|---|---|
| **잘못된 순서** | `HeroBannerCarousel` → "오늘의 핫플 🔥" + `HotPlaceRow` |
| **올바른 순서** | "오늘의 핫플 🔥" → `HeroBannerCarousel` → "실시간 팝업 트렌드" + `HotPlaceRow` |

### 원인 2 — BannerPage에 이미지 없음
`BannerPage`가 그라디언트만 렌더링하고, `store.imageUrl`을 Coil로 로딩하는 코드가 없었음.

### 해결

**섹션 순서 재배치:**
```kotlin
// HomeContent
SectionHeader("오늘의 핫플 🔥")
HeroBannerCarousel(stores = uiState.hotStores)  // 헤더 아래로 이동

SectionHeader("실시간 팝업 트렌드 🔍")
HotPlaceRow(stores = uiState.hotStores)         // 카드 목록으로 재매핑
```

**BannerPage 3-레이어 구조로 재작성:**
```kotlin
Box(modifier = Modifier.fillMaxSize()) {
    // 레이어 1: 배경 이미지 (없으면 그라디언트)
    if (store.imageUrl.isNotBlank()) {
        AsyncImage(contentScale = ContentScale.Crop, ...)
    } else {
        Box(modifier = Modifier.background(gradient))
    }
    // 레이어 2: 텍스트 가독성용 다크 그라디언트 오버레이
    Box(modifier = Modifier.background(verticalGradient))
    // 레이어 3: 팝업 이름 + 날짜 + CTA 버튼
    Column(modifier = Modifier.align(Alignment.BottomStart)) { ... }
}
```

---

## 4. Naver Blog Search API HTTP 401 오류

### 영향 화면
`PopupFeedScreen`

### 증상
```
데이터를 불러오지 못했어요 HTTP 401
```

### 원인
`local.properties`의 `naver.client.secret` 값이 잘못 설정되어 있음.

| 항목 | 확인 결과 |
|---|---|
| Base URL | `https://openapi.naver.com/` ✅ |
| 헤더명 | `X-Naver-Client-Id` / `X-Naver-Client-Secret` ✅ |
| `naver.client.id` | Naver Maps와 동일한 값 사용 중 ⚠️ |
| `naver.client.secret` | 10자리 — 유효한 Secret 형식 아님 ❌ |

### 핵심 개념
Naver Open API는 **두 가지 완전히 다른 플랫폼**에서 키를 발급한다:

| 서비스 | 플랫폼 | 키 발급처 |
|---|---|---|
| 네이버 지도 SDK | Naver Cloud Platform | `console.ncloud.com` |
| 블로그 검색 API | **Naver Developers** | **`developers.naver.com`** |

### 해결 방법
1. `https://developers.naver.com/apps` 접속
2. 앱 등록 후 **검색 > 블로그** API 사용 추가
3. 발급된 Client ID / Client Secret을 `local.properties`에 업데이트

```properties
# local.properties
naver.client.id=발급받은_CLIENT_ID
naver.client.secret=발급받은_CLIENT_SECRET
```

> 코드 자체(Base URL, 헤더명, 엔드포인트)는 모두 올바르게 구현되어 있음.

---

## 5. NearbyScreen 레이아웃 깨짐

### 영향 화면
`NearbyScreen`

### 증상
1. "서울" TopBar 텍스트가 StatusBar 뒤에 가려져 보이지 않음
2. 지도가 StatusBar 영역까지 침범
3. BottomNavBar가 화면에서 사라짐 (Scaffold 수정 이후)

---

### 5-1. StatusBar 침범 + TopBar 없음

**원인:**  
`NearbyScreen`에 `Scaffold`가 없어 TopBar 자체가 존재하지 않았고,  
`enableEdgeToEdge()` 사용 환경에서 `NaverMap(fillMaxSize())`이 y=0(StatusBar 영역)부터 렌더링됨.

**해결 — `Scaffold` + `TopBar` 추가 (1차 시도):**
```kotlin
Scaffold(
    topBar = { CenterAlignedTopAppBar("서울", windowInsets = WindowInsets(0)) },
    contentWindowInsets = WindowInsets(0)
) { innerPadding ->
    Box(Modifier.fillMaxSize().padding(innerPadding)) { ... }
}
```
→ StatusBar 침범 해결 ✅ / "서울" 텍스트가 StatusBar 뒤에 가려짐 ❌

---

### 5-2. CenterAlignedTopAppBar windowInsets 수정 시 BottomNavBar 소실

**원인:**  
`CenterAlignedTopAppBar`의 `windowInsets` 파라미터를 변경하거나 `contentWindowInsets = WindowInsets(0)`을 제거하면, 내부 Scaffold의 windowInsets 계산이 **외부 NavGraph Scaffold와 충돌**하여 BottomNavBar가 가려지거나 사라짐.

**재현 조건:**
```
외부 NavGraph Scaffold: contentWindowInsets = WindowInsets(0)
내부 NearbyScreen Scaffold: contentWindowInsets 미설정 (default)
→ 시스템 네비게이션 바 insets 이중 처리 → BottomNavBar 소실
```

---

### 5-3. 최종 해결 — Scaffold 제거, Column + statusBarsPadding() 사용

**핵심 원칙:**  
내부 Scaffold를 사용하지 않고, `statusBarsPadding()`으로 StatusBar를 직접 처리.

```kotlin
@Composable
fun NearbyScreen(...) {
    Column(modifier = Modifier.fillMaxSize()) {

        // TopBar: statusBarsPadding()으로 StatusBar 높이 직접 처리
        // → Scaffold windowInsets 충돌 없음
        Surface(
            color    = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .statusBarsPadding()   // StatusBar 높이만큼 패딩
                    .fillMaxWidth()
                    .height(56.dp),        // 콘텐츠 고정 높이
                contentAlignment = Alignment.Center
            ) {
                Text("서울", style = MaterialTheme.typography.titleMedium)
            }
        }

        // 지도 영역: weight(1f)로 TopBar와 BottomNavBar 사이를 정확히 채움
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            NearbyMapContent(...)
        }
    }
}
```

**결과:**

| 항목 | 결과 |
|---|---|
| "서울" TopBar 텍스트 | StatusBar 아래 정상 표시 ✅ |
| 지도 시작 위치 | TopBar 바로 아래 ✅ |
| StatusBar 침범 | 없음 ✅ |
| BottomNavBar | 정상 표시 ✅ |

---

## 교훈 요약

| 이슈 | 교훈 |
|---|---|
| 로컬 private 함수 이름 충돌 | 공유 컴포넌트 도입 시 기존 로컬 함수 먼저 삭제 |
| Naver API 키 혼용 | Naver Cloud(지도)와 Naver Developers(블로그 검색)는 완전히 별개의 플랫폼 |
| 섹션 순서 오류 | UI 컴포저블 배치 순서가 디자인과 1:1 대응하는지 항상 확인 |
| Scaffold 중첩 windowInsets | 내부 Scaffold가 필요 없으면 Column + 수동 패딩으로 대체 |
| `collectAsState` 규칙 위반 | 프로젝트 규칙: `collectAsStateWithLifecycle()` 사용 (`collectAsState()` 금지) |
