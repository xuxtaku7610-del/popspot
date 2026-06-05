# PopSpot 네이버 지도 SDK 연결 방법

## 1. local.properties 작성

프로젝트 루트에 `local.properties` 파일을 만들고 아래 값을 넣습니다.

```properties
naver.client.id=네이버_블로그검색_CLIENT_ID
naver.client.secret=네이버_블로그검색_CLIENT_SECRET
naver.map.client.id=네이버_지도_SDK_NCP_KEY_ID
tour.api.key=TourAPI_서비스키
```

`naver.map.client.id`는 네이버 클라우드 플랫폼 Maps의 **NCP Key ID**입니다. 네이버 개발자센터 블로그 검색 Client ID와 다른 값일 수 있습니다.

기존 코드 호환을 위해 `NAVER_MAP_KEY_ID`도 읽을 수 있지만, 새로 입력할 때는 `naver.map.client.id`를 권장합니다.

## 2. 네이버 클라우드 플랫폼 등록값

네이버 클라우드 플랫폼 > Application Services > Maps에서 Android 앱을 등록합니다.

필요한 값:

- 패키지명: `com.popspot.app`
- SHA-1: 실행에 사용하는 keystore의 SHA-1
- 서비스: Dynamic Map 선택

## 3. SHA-1 주의

현재 Gradle 설정은 아래처럼 동작합니다.

- `app/popspot-release-key.jks` 또는 `popspot-release-key.jks`가 있고
- `RELEASE_STORE_PASSWORD`, `RELEASE_KEY_PASSWORD`가 입력되어 있으면
- debug 빌드도 해당 `.jks`로 서명합니다.

이 경우 네이버 콘솔에는 `.jks`에서 추출한 SHA-1을 등록하면 됩니다.

반대로 `.jks`나 비밀번호가 없으면 Android Studio 기본 debug keystore로 빌드됩니다. 이 경우 네이버 콘솔에는 debug keystore SHA-1을 등록해야 지도 인증이 됩니다.

## 4. 실행 확인

Android Studio에서 Gradle Sync 후 앱을 실행하고 하단 탭의 `내 주변` 화면으로 이동합니다.

정상 연결 시:

- 네이버 지도가 표시됩니다.
- 현재 위치 권한을 허용하면 현재 위치 기준으로 카메라가 이동합니다.
- 서울 주요 지역의 팝업스토어 샘플 마커가 지도에 표시됩니다.
- 검색창에서 이름, 카테고리, 주소로 마커 필터링이 됩니다.
- 마커를 누르면 하단 정보 카드가 뜹니다.
