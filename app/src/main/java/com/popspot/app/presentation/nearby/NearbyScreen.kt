package com.popspot.app.presentation.nearby

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.popspot.app.BuildConfig
import com.popspot.app.data.remote.api.NaverMapApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.launch
import kotlin.math.*

private const val TAG = "MAP_SEARCH_SCREEN"

// Retrofit 인스턴스를 외부에서 생성하여 리컴포지션 시 부하 방지
private val naverMapApi by lazy {
    Retrofit.Builder()
        .baseUrl("https://naveropenapi.apigw.ntruss.com/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NaverMapApi::class.java)
}

@Composable
fun NearbyScreen(
    modifier: Modifier = Modifier
) {
    Log.d(TAG, "NearbyScreen Composing...")
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    // 상태 관리
    var myRealLocation by remember { mutableStateOf<LatLng?>(null) }
    var query by remember { mutableStateOf("") }
    var searchedLocationName by remember { mutableStateOf("울산") }
    var currentCenter by remember { mutableStateOf(LatLng(35.5384, 129.3114)) }
    var selectedPopup by remember { mutableStateOf<PopupPlace?>(null) }
    var nearbyPopups by remember { mutableStateOf(emptyList<PopupPlace>()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isSearching by remember { mutableStateOf(false) }

    var naverMapRef by remember { mutableStateOf<NaverMap?>(null) }
    val popupMarkers = remember { mutableStateListOf<Marker>() }
    var searchMarker by remember { mutableStateOf<Marker?>(null) }

    // 위치 클라이언트
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // 권한 요청 런처
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                      permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            try {
                fusedLocationClient
                    .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token)
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            myRealLocation = LatLng(location.latitude, location.longitude)
                            Log.d(TAG, "Current GPS acquired: ${location.latitude}, ${location.longitude}")
                        } else {
                            fusedLocationClient.lastLocation.addOnSuccessListener { lastLocation ->
                                lastLocation?.let {
                                    myRealLocation = LatLng(it.latitude, it.longitude)
                                    Log.d(TAG, "Last GPS acquired: ${it.latitude}, ${it.longitude}")
                                }
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e(TAG, "Current location request failed", exception)
                    }
            } catch (e: SecurityException) { Log.e(TAG, "Loc Error", e) }
        }
    }

    // 초기 권한 체크 및 데이터 로드
    LaunchedEffect(Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            try {
                fusedLocationClient
                    .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token)
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            myRealLocation = LatLng(location.latitude, location.longitude)
                            Log.d(TAG, "Initial current location: ${location.latitude}, ${location.longitude}")
                        } else {
                            fusedLocationClient.lastLocation.addOnSuccessListener { lastLocation ->
                                lastLocation?.let {
                                    myRealLocation = LatLng(it.latitude, it.longitude)
                                    Log.d(TAG, "Initial last location: ${it.latitude}, ${it.longitude}")
                                }
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e(TAG, "Initial current location request failed", exception)
                    }
            } catch (e: SecurityException) { Log.e(TAG, "Initial loc error", e) }
        } else {
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        }

        // 초기 데이터 로딩
        nearbyPopups = queryPopupPlaces(distanceFrom = myRealLocation)
    }

    // 내 위치가 확보되면 지도를 내 위치로 이동하고, 카드 거리를 내 위치 기준으로 다시 계산한다.
    LaunchedEffect(myRealLocation, naverMapRef) {
        myRealLocation?.let { location ->
            Log.d(TAG, "Updating map and distances based on real location: $location")
            currentCenter = location
            searchedLocationName = "내 위치"

            val map = naverMapRef
            val bounds = runCatching { map?.contentBounds?.toMapBounds() }.getOrNull()
            nearbyPopups = queryPopupPlaces(distanceFrom = location, bounds = bounds)

            map?.moveCamera(
                CameraUpdate.scrollAndZoomTo(location, 14.5)
                    .animate(CameraAnimation.Easing)
            )
        }
    }

    // 내부 함수들
    fun clearPopupMarkers() {
        popupMarkers.forEach { it.map = null }
        popupMarkers.clear()
    }

    fun renderMarkers(naverMap: NaverMap, popups: List<PopupPlace>) {
        clearPopupMarkers()
        popups.forEach { popup ->
            Marker().apply {
                position = LatLng(popup.latitude, popup.longitude)
                captionText = popup.name
                subCaptionText = popup.category
                width = 90
                height = 110
                setOnClickListener {
                    selectedPopup = popup
                    naverMap.moveCamera(CameraUpdate.scrollAndZoomTo(position, 15.5).animate(CameraAnimation.Easing))
                    true
                }
                map = naverMap
                popupMarkers.add(this)
            }
        }
    }

    fun updateSearchMarker(naverMap: NaverMap, position: LatLng, name: String) {
        searchMarker?.map = null
        searchMarker = Marker().apply {
            this.position = position
            captionText = name
            subCaptionText = "검색 위치"
            map = naverMap
        }
    }

    fun moveToLocation(naverMap: NaverMap, center: LatLng, name: String, moveCamera: Boolean = true) {
        currentCenter = center
        searchedLocationName = name
        selectedPopup = null
        
        val bounds = runCatching { naverMap.contentBounds.toMapBounds() }.getOrNull()
        val found = queryPopupPlaces(distanceFrom = myRealLocation, bounds = bounds)
        nearbyPopups = found

        if (moveCamera) {
            naverMap.moveCamera(CameraUpdate.scrollAndZoomTo(center, 14.5).animate(CameraAnimation.Easing))
        }
        updateSearchMarker(naverMap, center, name)
        renderMarkers(naverMap, found)
    }

    fun refreshByVisibleArea(naverMap: NaverMap, name: String? = null) {
        val center = naverMap.cameraPosition.target
        val bounds = runCatching { naverMap.contentBounds.toMapBounds() }.getOrNull()
        val found = queryPopupPlaces(distanceFrom = myRealLocation, bounds = bounds)
        
        currentCenter = center
        searchedLocationName = name ?: searchedLocationName
        nearbyPopups = found
        renderMarkers(naverMap, found)
    }

    fun searchLocation() {
        val kw = query.trim()
        if (kw.isBlank()) return
        val map = naverMapRef ?: return
        
        keyboardController?.hide()
        isSearching = true
        errorMessage = null

        coroutineScope.launch {
            var res = geocodeWithNaver(naverMapApi, kw) ?: fallbackGeocode(kw)
            isSearching = false
            if (res == null) {
                errorMessage = "위치를 찾지 못했어. (예: 성수, 홍대, 울산)"
            } else {
                moveToLocation(map, res.latLng, res.name)
            }
        }
    }

    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                MapView(ctx).apply {
                    onCreate(Bundle())
                    getMapAsync { naverMap ->
                        naverMapRef = naverMap
                        naverMap.uiSettings.apply {
                            isZoomControlEnabled = true
                            isLocationButtonEnabled = false
                        }
                        moveToLocation(naverMap, currentCenter, searchedLocationName)

                        naverMap.addOnCameraIdleListener {
                            coroutineScope.launch {
                                val addr = reverseGeocodeWithNaver(naverMapApi, naverMap.cameraPosition.target)
                                refreshByVisibleArea(naverMap, addr)
                            }
                        }
                        naverMap.setOnMapClickListener { _, latLng ->
                            coroutineScope.launch {
                                val addr = reverseGeocodeWithNaver(naverMapApi, latLng)
                                moveToLocation(naverMap, latLng, addr)
                            }
                        }
                    }
                }
            },
            update = { view ->
                // 필요 시 업데이트 로직
            }
        )

        MapSearchPanel(
            query = query,
            onQueryChange = { query = it },
            onSearch = { searchLocation() },
            isSearching = isSearching,
            errorMessage = errorMessage
        )

        NearbyPopupPanel(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            locationName = searchedLocationName,
            popups = nearbyPopups,
            selectedPopup = selectedPopup,
            onPopupClick = { popup ->
                selectedPopup = popup
                naverMapRef?.moveCamera(CameraUpdate.scrollAndZoomTo(LatLng(popup.latitude, popup.longitude), 15.5).animate(CameraAnimation.Easing))
            }
        )
    }
}


@Composable
private fun MapSearchPanel(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    isSearching: Boolean,
    errorMessage: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { newValue ->
                        // 한글/영어/숫자/공백/주소 특수문자 전부 허용
                        onQueryChange(newValue)
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = {
                        Text("지역이나 주소 검색")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = { onSearch() }
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onSearch,
                    enabled = !isSearching,
                    modifier = Modifier.height(56.dp)
                ) {
                    if (isSearching) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("검색")
                    }
                }
            }

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun NearbyPopupPanel(
    modifier: Modifier = Modifier,
    locationName: String,
    popups: List<PopupPlace>,
    selectedPopup: PopupPlace?,
    onPopupClick: (PopupPlace) -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.96f))
            .padding(top = 12.dp, bottom = 18.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "$locationName 주변 팝업스토어",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (popups.isEmpty()) {
            Text(
                text = "주변 5km 안에 등록된 팝업스토어가 없어요.",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(popups) { popup ->
                    PopupPlaceCard(
                        popup = popup,
                        selected = selectedPopup?.id == popup.id,
                        onClick = { onPopupClick(popup) }
                    )
                }
            }
        }
    }
}

@Composable
private fun PopupPlaceCard(
    popup: PopupPlace,
    selected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
    }

    Card(
        modifier = Modifier
            .width(230.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(width = 1.dp, brush = androidx.compose.ui.graphics.SolidColor(borderColor)),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Store,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = popup.category,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = popup.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = popup.address,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (popup.distanceKm.isNaN()) {
                    "현재 위치 확인 후 거리 표시"
                } else {
                    "내 위치에서 약 ${"%.1f".format(popup.distanceKm)}km"
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private data class GeocodeResult(
    val name: String,
    val latLng: LatLng
)

private data class PopupPlace(
    val id: Int,
    val name: String,
    val category: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val distanceKm: Double = 0.0
)

private data class MapBounds(
    val southWestLat: Double,
    val southWestLng: Double,
    val northEastLat: Double,
    val northEastLng: Double
) {
    fun contains(latitude: Double, longitude: Double): Boolean {
        val latInRange = latitude in southWestLat..northEastLat
        val lngInRange = if (southWestLng <= northEastLng) {
            longitude in southWestLng..northEastLng
        } else {
            // 지도 화면이 180도 경도선을 걸칠 때 대비
            longitude >= southWestLng || longitude <= northEastLng
        }
        return latInRange && lngInRange
    }
}

private fun com.naver.maps.geometry.LatLngBounds.toMapBounds(): MapBounds = MapBounds(
    southWestLat = southWest.latitude,
    southWestLng = southWest.longitude,
    northEastLat = northEast.latitude,
    northEastLng = northEast.longitude
)

private suspend fun geocodeWithNaver(
    api: NaverMapApi,
    keyword: String
): GeocodeResult? {
    return runCatching {
        val response = api.geocodeAddress(
            clientId = BuildConfig.NAVER_MAP_KEY_ID,
            clientSecret = BuildConfig.NAVER_MAP_CLIENT_SECRET,
            address = keyword
        )

        val first = response.addresses.firstOrNull() ?: return null
        val latitude = first.latitude.toDoubleOrNull() ?: return null
        val longitude = first.longitude.toDoubleOrNull() ?: return null

        GeocodeResult(
            name = first.roadAddress.ifBlank { first.jibunAddress.ifBlank { keyword } },
            latLng = LatLng(latitude, longitude)
        )
    }.getOrElse { exception ->
        Log.e(TAG, "Naver geocoding failed", exception)
        null
    }
}

private suspend fun reverseGeocodeWithNaver(
    api: NaverMapApi,
    latLng: LatLng
): String {
    return runCatching {
        val response = api.reverseGeocode(
            clientId = BuildConfig.NAVER_MAP_KEY_ID,
            clientSecret = BuildConfig.NAVER_MAP_CLIENT_SECRET,
            coords = "${latLng.longitude},${latLng.latitude}"
        )

        val result = response.results.firstOrNull() ?: return "현재 지도 위치"
        val region = result.region
        val land = result.land

        val areaText = listOfNotNull(
            region?.area1?.name,
            region?.area2?.name,
            region?.area3?.name,
            region?.area4?.name
        )
            .filter { it.isNotBlank() }
            .joinToString(" ")

        val landText = buildString {
            if (!land?.name.isNullOrBlank()) append(land?.name)
            if (!land?.number1.isNullOrBlank()) {
                if (isNotBlank()) append(" ")
                append(land?.number1)
            }
            if (!land?.number2.isNullOrBlank()) {
                append("-")
                append(land?.number2)
            }
        }

        listOf(areaText, landText)
            .filter { it.isNotBlank() }
            .joinToString(" ")
            .ifBlank { "현재 지도 위치" }
    }.getOrElse { exception ->
        Log.e(TAG, "Naver reverse geocoding failed", exception)
        "현재 지도 위치"
    }
}

private fun queryPopupPlaces(
    keyword: String? = null,
    distanceFrom: LatLng? = null,
    bounds: MapBounds? = null
): List<PopupPlace> {
    val trimmedKeyword = keyword?.trim().orEmpty()

    return samplePopupPlaces
        .asSequence()
        // 요구사항 2: keyword는 선택 파라미터. 비어 있으면 필터하지 않음.
        .filter { popup ->
            trimmedKeyword.isBlank() ||
                    popup.name.contains(trimmedKeyword, ignoreCase = true) ||
                    popup.category.contains(trimmedKeyword, ignoreCase = true) ||
                    popup.address.contains(trimmedKeyword, ignoreCase = true)
        }
        // 요구사항 1 + BBox: 현재 지도 화면 사각형 영역 안의 데이터만 표시
        .filter { popup ->
            bounds == null || bounds.contains(popup.latitude, popup.longitude)
        }
        .map { popup ->
            if (distanceFrom == null) {
                popup.copy(distanceKm = Double.NaN)
            } else {
                popup.copy(
                    distanceKm = distanceKm(
                        lat1 = distanceFrom.latitude,
                        lon1 = distanceFrom.longitude,
                        lat2 = popup.latitude,
                        lon2 = popup.longitude
                    )
                )
            }
        }
        .toList()
        .let { list ->
            // 지도 이동 위치가 아니라 내 현재 위치가 있을 때만 거리순 정렬
            if (distanceFrom != null) list.sortedBy { it.distanceKm } else list
        }
}

private fun findNearbyPopups(center: LatLng): List<PopupPlace> {
    return queryPopupPlaces(distanceFrom = center)
        .filter { it.distanceKm <= 5.0 }
}

private fun distanceKm(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {
    val earthRadiusKm = 6371.0

    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)

    val a = sin(dLat / 2).pow(2.0) +
            cos(Math.toRadians(lat1)) *
            cos(Math.toRadians(lat2)) *
            sin(dLon / 2).pow(2.0)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadiusKm * c
}

private val samplePopupPlaces = listOf(
    PopupPlace(
        id = 1,
        name = "울산 삼산 라이프스타일 팝업",
        category = "라이프스타일",
        address = "울산 남구 삼산동",
        latitude = 35.5396,
        longitude = 129.3358
    ),
    PopupPlace(
        id = 2,
        name = "성남동 빈티지 마켓",
        category = "패션",
        address = "울산 중구 성남동",
        latitude = 35.5547,
        longitude = 129.3203
    ),
    PopupPlace(
        id = 3,
        name = "울산대 디저트 팝업",
        category = "푸드",
        address = "울산 남구 대학로",
        latitude = 35.5438,
        longitude = 129.2564
    ),
    PopupPlace(
        id = 4,
        name = "성수 브랜드 쇼룸",
        category = "브랜드",
        address = "서울 성동구 성수동",
        latitude = 37.5446,
        longitude = 127.0557
    ),
    PopupPlace(
        id = 5,
        name = "성수 굿즈 팝업",
        category = "굿즈",
        address = "서울 성동구 연무장길",
        latitude = 37.5421,
        longitude = 127.0565
    ),
    PopupPlace(
        id = 6,
        name = "홍대 캐릭터 팝업",
        category = "캐릭터",
        address = "서울 마포구 홍대입구",
        latitude = 37.5571,
        longitude = 126.9245
    ),
    PopupPlace(
        id = 7,
        name = "강남 뷰티 팝업",
        category = "뷰티",
        address = "서울 강남구 강남대로",
        latitude = 37.4979,
        longitude = 127.0276
    ),
    PopupPlace(
        id = 8,
        name = "잠실 스포츠 팝업",
        category = "스포츠",
        address = "서울 송파구 잠실동",
        latitude = 37.5133,
        longitude = 127.1002
    ),
    PopupPlace(
        id = 9,
        name = "부산 서면 스트릿 팝업",
        category = "패션",
        address = "부산 부산진구 서면",
        latitude = 35.1577,
        longitude = 129.0592
    ),
    PopupPlace(
        id = 10,
        name = "해운대 여름 팝업",
        category = "시즌",
        address = "부산 해운대구",
        latitude = 35.1631,
        longitude = 129.1635
    )
)

private fun fallbackGeocode(keyword: String): GeocodeResult? {
    val normalized = keyword.trim().lowercase()
    val locations = mapOf(
        "울산" to LatLng(35.5384, 129.3114),
        "성남동" to LatLng(35.5547, 129.3203),
        "삼산" to LatLng(35.5396, 129.3358),
        "성수" to LatLng(37.5446, 127.0557),
        "홍대" to LatLng(37.5571, 126.9245),
        "강남" to LatLng(37.4979, 127.0276),
        "잠실" to LatLng(37.5133, 127.1002),
        "부산" to LatLng(35.1796, 129.0756),
        "제주" to LatLng(33.4996, 126.5312)
    )

    val matched = locations.entries.firstOrNull {
        normalized.contains(it.key.lowercase())
    } ?: return null

    return GeocodeResult(
        name = matched.key,
        latLng = matched.value
    )
}