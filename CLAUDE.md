# PopSpot — Android 프로젝트

## 개요
- 플랫폼: Android (Jetpack Compose)
- 언어: Kotlin
- 아키텍처: MVVM + Clean Architecture
- 최소 SDK: API 26 (Android 8.0) / Target SDK: API 35
- API: 네이버 지도 SDK, 공공데이터 포털
- 브랜드 컬러: Primary `#7B5CF5` (보라)

---

## 폴더 구조
```
app/src/main/java/.../
├── data/
│   ├── model/          # Entity, DTO 데이터 클래스
│   ├── remote/         # Retrofit API 서비스 인터페이스
│   ├── local/          # Room DB (스크랩 저장)
│   └── repository/     # Repository 구현체
├── domain/
│   ├── model/          # 비즈니스 모델 (UI와 무관한 순수 데이터)
│   ├── repository/     # Repository 인터페이스
│   └── usecase/        # UseCase 클래스 (1 클래스 = 1 기능)
└── presentation/
    ├── ui/             # Composable 화면
    │   ├── home/       # 메인 홈
    │   ├── trend/      # 팝업 트렌드
    │   ├── event/      # 공식 행사
    │   ├── scrap/      # 스크랩
    │   └── mypage/     # 마이페이지
    ├── viewmodel/      # ViewModel (1화면 = 1ViewModel)
    └── component/      # 재사용 Composable 컴포넌트
```

---

## 아키텍처 규칙
- UI → ViewModel → UseCase → Repository 단방향 의존
- UI에서 Repository 또는 UseCase 직접 접근 금지
- UI 상태: `sealed class UiState` (Loading / Success / Error)
- 상태 노출: `StateFlow` / 구독: `collectAsStateWithLifecycle`
- Repository: 인터페이스(domain)와 구현체(data) 분리
- 의존성 주입: Hilt 사용

---

## 핵심 의존성
| 라이브러리 | 용도 |
|---|---|
| Jetpack Compose BOM | UI |
| Hilt | 의존성 주입 |
| Retrofit + OkHttp | 네트워크 |
| Room | 로컬 DB (스크랩) |
| Naver Map SDK | 지도 |
| Coil | 이미지 로딩 |
| Coroutines + Flow | 비동기 처리 |

---

## 코딩 컨벤션
| 항목 | 규칙 |
|---|---|
| Composable 파일 | PascalCase.kt |
| 클래스·Composable 함수 | PascalCase |
| 변수·함수 | camelCase |
| 컴포넌트 재사용 기준 | 2곳 이상 사용 시 `presentation/component/`로 이동 |
| ViewModel | 1 화면 = 1 ViewModel 원칙 |
| Composable Preview | 화면·컴포넌트별 `@Preview` 작성 |

---

## 디자인 시스템
상세 UI 스펙 및 화면별 개선 프롬프트 → `UI_개선_프롬프트.md` 참고

---

## 개발 체크리스트
```
[ ] UI → ViewModel → UseCase → Repository 단방향 의존 준수
[ ] UiState sealed class로 상태 관리 (Loading/Success/Error)
[ ] HTTP 오류 코드 직접 노출 금지 — 사용자 친화적 메시지로 변환
[ ] 빈 상태(Empty State): 아이콘 + 메시지 + 액션 버튼 필수
[ ] 모든 터치 타겟: 최소 44×44dp
[ ] 이미지 로딩: Coil 사용
[ ] 스크랩 데이터: Room DB 저장
[ ] Composable @Preview 작성
[ ] Hilt 의존성 주입 적용
[ ] collectAsStateWithLifecycle 사용 (collectAsState 사용 금지)
```
