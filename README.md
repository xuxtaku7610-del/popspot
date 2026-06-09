# 📍 PopSpot — 팝업스토어 알림이

> 네이버 지도 + 공공데이터 포털 기반의 팝업스토어 탐색 Android 앱

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean-orange)

---

## 📱 프로젝트 소개

팝업스토어는 MZ세대를 중심으로 빠르게 성장하는 트렌드임에도 불구하고,
관련 정보를 한눈에 확인할 수 있는 전용 앱이 없었습니다.

**PopSpot**은 이 불편함을 해결하기 위해 기획된 팝업스토어 전용 탐색 앱입니다.

- 현재 진행 중인 팝업스토어를 지도 위에서 직관적으로 탐색
- 네이버 블로그 기반 실시간 팝업 소식 제공
- 관심 있는 팝업스토어를 스크랩하여 저장
- 공식 행사 및 트렌드 정보 제공

---

## 🖼️ 주요 화면

### 🏠 홈 화면
오늘의 핫플, 실시간 팝업 트렌드, 이번 주 공식 행사

<img width="256" height="564" alt="홈 화면 1" src="https://github.com/user-attachments/assets/1e1da0d5-ee0b-4a04-bf03-c53f6862e416" />
<img width="255" height="556" alt="홈 화면 2" src="https://github.com/user-attachments/assets/aa40300d-a03b-47f3-922a-72970a11199a" />

---

### 🗺️ 팝업 지도
네이버 지도 기반 팝업스토어 위치 탐색

<img width="254" height="545" alt="팝업 지도" src="https://github.com/user-attachments/assets/cea1ab10-b559-4bff-afe9-0a00cb554067" />

---

### 🔖 스크랩
관심 팝업스토어 저장 및 관리

<img width="253" height="553" alt="스크랩" src="https://github.com/user-attachments/assets/d2eade57-0c41-4c07-a391-2dea44ca8c9a" />

---

### 👤 마이페이지
사용자 설정 및 프로필 관리

<img width="254" height="559" alt="마이페이지 1" src="https://github.com/user-attachments/assets/0c1c380b-dfcc-40ef-bf23-410c6e453968" />
<img width="234" height="499" alt="마이페이지 2" src="https://github.com/user-attachments/assets/2a58c528-e6a7-4f90-8409-d49de1a2dc4e" />

---

## ✨ 주요 기능

### 🏠 홈 화면

- **오늘의 핫플**: 현재 가장 인기 있는 팝업스토어 카드 형태로 표시
- **실시간 팝업 트렌드**: 네이버 블로그 검색 API 기반 최신 팝업 소식
- **이번 주 공식 행사**: 공공데이터 포털 연동 행사 정보
- 지역 필터 (`#전체` `#성수` `#홍대` `#강남` `#캐릭터` 등)
- 팝업스토어 검색 기능

### 🗺️ 팝업 지도

- 네이버 지도 SDK 기반 실시간 위치 마커 표시
- 팝업스토어 클릭 시 상세 정보 표시
- 현재 위치 기반 주변 팝업스토어 탐색

### 📅 공식 행사

- 공공데이터 포털 API 연동
- 날짜별 행사 정보 제공

### 🔖 스크랩

- Room DB 기반 로컬 저장
- 관심 팝업스토어 즐겨찾기

---

## 🏗️ 아키텍처

```
[ UI (Compose) ]
      ↕  observe
[ ViewModel ]             ← Presentation Layer
      ↕  call
[ UseCase ]               ← Domain Layer (비즈니스 로직)
      ↕  call
[ Repository Interface ]  ← Domain Layer
      ↕  implement
[ RepositoryImpl ]        ← Data Layer
      ↕  call
[ Retrofit / Naver SDK / Room ]  ← Remote & Local Data Source
```

> 의존성 방향은 항상 **안쪽(Domain)**을 향합니다.
> Domain 레이어는 Retrofit도, Android SDK도 모르기 때문에 독립적으로 테스트 가능합니다.

---

## 📁 프로젝트 구조

```
app/
├── data/
│   ├── remote/
│   │   ├── api/
│   │   │   ├── NaverMapApi.kt          # 네이버 지도 Retrofit 인터페이스
│   │   │   ├── NaverBlogApi.kt         # 네이버 블로그 검색 API
│   │   │   └── PublicDataApi.kt        # 공공데이터 포털 API
│   │   └── dto/                        # API 응답 모델
│   ├── local/
│   │   └── db/                         # Room DB (스크랩 저장)
│   ├── repository/                     # Repository 구현체
│   └── di/
│       ├── NetworkModule.kt            # 네트워크 객체 (Retrofit, OkHttp)
│       ├── RepositoryModule.kt         # Repository 바인딩
│       └── DatabaseModule.kt          # Room DB
│
├── domain/
│   ├── model/                          # 도메인 모델
│   ├── repository/                     # Repository 인터페이스
│   └── usecase/                        # 비즈니스 로직
│
├── presentation/
│   ├── home/                           # 홈 화면
│   ├── map/                            # 팝업 지도 화면
│   ├── feed/                           # 팝업 드래프트 피드
│   ├── scrap/                          # 스크랩 화면
│   ├── mypage/                         # 마이페이지
│   └── ui/theme/                       # 앱 테마 & 디자인 시스템
│
└── MainActivity.kt
```

---

## 🔧 기술 스택

| 분류    | 라이브러리                  | 버전           | 용도           |
|-------|------------------------|--------------|--------------|
| UI    | Jetpack Compose BOM    | 2024.05.00   | 선언형 UI       |
| 지도    | naver-map-compose      | 1.7.2        | 지도 렌더링       |
| 네트워크  | Retrofit2              | 2.11.0       | REST API 통신  |
| 네트워크  | OkHttp3                | 4.12.0       | HTTP 클라이언트   |
| JSON  | Gson                   | —            | API 응답 역직렬화  |
| 비동기   | Kotlin Coroutines      | 1.8.0        | 비동기 처리       |
| DI    | Hilt                   | 2.51         | 의존성 주입       |
| 상태관리  | ViewModel + StateFlow  | 2.8.0        | UI 상태 관리     |
| DB    | Room                   | —            | 스크랩 로컬 저장    |
| 내비게이션 | Navigation Compose     | —            | 화면 전환        |
| 테스트   | JUnit4 + MockK         | —            | 단위 테스트       |

---

## 👥 기여 내역

> 초기 개발 전체 기록: [`my-work` 브랜치](https://github.com/xuxtaku7610-del/popspot/tree/my-work)

### 기획 및 전체 개발 담당

앱의 기획, 설계, UI 디자인, 핵심 기능 구현 전담

#### 🗂️ 기획

- 팝업스토어 전용 앱의 필요성 기획 (기존 유사 앱 부재)
- 홈 / 지도 / 공식 행사 / 스크랩 / 마이페이지 5탭 구조 설계
- 지역 필터, 검색, 스크랩 등 핵심 기능 정의
- 전체 사용자 흐름(User Flow) 설계

#### 🎨 UI 디자인

- 보라색 계열 브랜드 컬러 및 디자인 시스템 설계
- 홈 화면 카드 UI, 태그 필터, 뉴스피드 레이아웃 설계
- 하단 네비게이션 바 5탭 구조 설계
- 전체 화면 Jetpack Compose로 구현

#### 🏛️ 아키텍처 설계

- MVVM + Clean Architecture 3계층 구조 설계
- Domain / Data / Presentation 레이어 분리
- Repository 패턴 및 UseCase 구조 설계
- Hilt 의존성 주입 구성

#### ⚙️ 핵심 기능 구현

- 로그인 화면 및 인증 흐름 구현
- 홈 화면 (핫플, 트렌드, 공식 행사 섹션) 구현
- 지역 필터 태그 (`#성수` `#홍대` `#강남` 등) 구현
- 스크랩 기능 (Room DB 연동) 구현
- Navigation Compose 기반 화면 전환 구현
- API 키 보안 처리 구조 설계 (`local.properties` 방식)

---

## 🚀 시작하기

### 1단계. 사전 요구사항 확인

- Android Studio Hedgehog 이상
- JDK 17 이상
- Android SDK 35

### 2단계. API 키 발급

**네이버 클라우드 플랫폼**

1. [console.ncloud.com](https://console.ncloud.com) 가입 및 앱 등록
2. Maps → Android SDK, Geocoding API 사용 신청

**공공데이터 포털**

1. [data.go.kr](https://data.go.kr) 가입
2. 원하는 서비스 API 활용 신청

### 3단계. local.properties 설정

`local.properties.example`을 복사해서 `local.properties`로 만든 뒤 키를 입력합니다.

```bash
cp local.properties.example local.properties
```

```properties
naver.client.id=YOUR_NAVER_CLIENT_ID
naver.client.secret=YOUR_NAVER_CLIENT_SECRET
public.data.api.key=YOUR_PUBLIC_DATA_SERVICE_KEY
tour.api.key=YOUR_TOUR_API_KEY
```

> ⚠️ `local.properties`는 `.gitignore`에 포함되어 있어 Git에 커밋되지 않습니다.

### 4단계. 빌드 및 실행

```bash
git clone https://github.com/xuxtaku7610-del/popspot.git
cd popspot
./gradlew assembleDebug
```

---

## ⚠️ 보안 주의사항

| 항목                    | 내용                                    |
|-----------------------|---------------------------------------|
| `local.properties`    | API 키 저장 — Git 커밋 금지 (gitignore 처리됨) |
| `*.jks`, `*.keystore` | 앱 서명 키 — Git 커밋 금지 (gitignore 처리됨)   |
| 네이버 지도                | SHA-1 지문 미등록 시 지도 미표시                 |
| 공공데이터 API             | 서비스별 1일 트래픽 제한 있음                     |
