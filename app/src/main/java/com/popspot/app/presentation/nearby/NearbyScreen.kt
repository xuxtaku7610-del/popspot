package com.popspot.app.presentation.nearby

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
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
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.math.*

private const val TAG = "MAP_SEARCH_SCREEN"

@Composable
fun NearbyScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    var query by remember { mutableStateOf("") }
    var searchedLocationName by remember { mutableStateOf("울산") }
    var currentCenter by remember { mutableStateOf(LatLng(35.5384, 129.3114)) }
    var selectedPopup by remember { mutableStateOf<PopupPlace?>(null) }
    var nearbyPopups by remember { mutableStateOf(findNearbyPopups(currentCenter)) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isSearching by remember { mutableStateOf(false) }

    var naverMapRef by remember { mutableStateOf<NaverMap?>(null) }
    val popupMarkers = remember { mutableStateListOf<Marker>() }
    var searchMarker by remember { mutableStateOf<Marker?>(null) }

    val mapView = remember {
        MapView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            onCreate(Bundle())
        }
    }

    fun clearPopupMarkers() {
        popupMarkers.forEach { it.map = null }
        popupMarkers.clear()
    }

    fun renderMarkers(
        naverMap: NaverMap,
        center: LatLng,
        locationName: String,
        popups: List<PopupPlace>
    ) {
        clearPopupMarkers()

        searchMarker?.map = null
        searchMarker = Marker().apply {
            position = center
            captionText = locationName
            subCaptionText = "검색 위치"
            map = naverMap
        }

        popups.forEach { popup ->
            val marker = Marker().apply {
                position = LatLng(popup.latitude, popup.longitude)
                captionText = popup.name
                subCaptionText = popup.category
                width = 90
                height = 110
                setOnClickListener {
                    selectedPopup = popup
                    naverMap.moveCamera(
                        CameraUpdate.scrollAndZoomTo(
                            LatLng(popup.latitude, popup.longitude),
                            15.5
                        ).animate(CameraAnimation.Easing)
                    )
                    true
                }
                map = naverMap
            }
            popupMarkers.add(marker)
        }
    }

    fun moveToLocation(
        naverMap: NaverMap,
        center: LatLng,
        locationName: String
    ) {
        currentCenter = center
        searchedLocationName = locationName
        selectedPopup = null

        val foundPopups = findNearbyPopups(center)
        nearbyPopups = foundPopups

        naverMap.moveCamera(
            CameraUpdate.scrollAndZoomTo(center, 14.5)
                .animate(CameraAnimation.Easing)
        )

        renderMarkers(
            naverMap = naverMap,
            center = center,
            locationName = locationName,
            popups = foundPopups
        )
    }

    fun searchLocation() {
        val keyword = query.trim()
        if (keyword.isBlank()) {
            errorMessage = "검색어를 입력해줘."
            return
        }

        val map = naverMapRef
        if (map == null) {
            errorMessage = "지도가 아직 준비되지 않았어."
            return
        }

        keyboardController?.hide()
        isSearching = true
        errorMessage = null

        coroutineScope.launch {
            val result = geocodeAddress(
                geocoder = Geocoder(context, Locale.KOREA),
                keyword = keyword
            ) ?: fallbackGeocode(keyword)

            isSearching = false

            if (result == null) {
                errorMessage = "주소나 지역을 찾지 못했어. 예: 울산, 성수, 홍대, 강남"
                return@launch
            }

            moveToLocation(
                naverMap = map,
                center = result.latLng,
                locationName = result.name
            )
        }
    }

    DisposableEffect(mapView) {
        mapView.onStart()
        mapView.onResume()

        mapView.getMapAsync { naverMap ->
            Log.d(TAG, "Official MapView ready")

            naverMapRef = naverMap

            naverMap.uiSettings.isZoomControlEnabled = true
            naverMap.uiSettings.isLocationButtonEnabled = false
            naverMap.uiSettings.isCompassEnabled = true
            naverMap.uiSettings.isScaleBarEnabled = true

            moveToLocation(
                naverMap = naverMap,
                center = currentCenter,
                locationName = searchedLocationName
            )
        }

        onDispose {
            clearPopupMarkers()
            searchMarker?.map = null
            mapView.onPause()
            mapView.onStop()
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { mapView }
        )

        MapSearchPanel(
            query = query,
            onQueryChange = { query = it },
            onSearch = { searchLocation() },
            isSearching = isSearching,
            errorMessage = errorMessage
        )

        NearbyPopupPanel(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            locationName = searchedLocationName,
            popups = nearbyPopups,
            selectedPopup = selectedPopup,
            onPopupClick = { popup ->
                selectedPopup = popup
                naverMapRef?.moveCamera(
                    CameraUpdate.scrollAndZoomTo(
                        LatLng(popup.latitude, popup.longitude),
                        15.5
                    ).animate(CameraAnimation.Easing)
                )
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
                text = "거리 약 ${"%.1f".format(popup.distanceKm)}km",
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

@Suppress("DEPRECATION")
private suspend fun geocodeAddress(
    geocoder: Geocoder,
    keyword: String
): GeocodeResult? = withContext(Dispatchers.IO) {
    runCatching {
        val result = geocoder.getFromLocationName(keyword, 1)
            ?.firstOrNull()

        result?.let {
            GeocodeResult(
                name = keyword,
                latLng = LatLng(it.latitude, it.longitude)
            )
        }
    }.getOrNull()
}

private fun fallbackGeocode(keyword: String): GeocodeResult? {
    val normalized = keyword.trim().lowercase()

    val locations = mapOf(
        "울산" to LatLng(35.5384, 129.3114),
        "울산대" to LatLng(35.5438, 129.2564),
        "삼산" to LatLng(35.5396, 129.3358),
        "성남동" to LatLng(35.5547, 129.3203),
        "서울" to LatLng(37.5665, 126.9780),
        "성수" to LatLng(37.5446, 127.0557),
        "홍대" to LatLng(37.5571, 126.9245),
        "강남" to LatLng(37.4979, 127.0276),
        "잠실" to LatLng(37.5133, 127.1002),
        "부산" to LatLng(35.1796, 129.0756),
        "서면" to LatLng(35.1577, 129.0592),
        "해운대" to LatLng(35.1631, 129.1635),
        "대구" to LatLng(35.8714, 128.6014),
        "대전" to LatLng(36.3504, 127.3845),
        "광주" to LatLng(35.1595, 126.8526),
        "인천" to LatLng(37.4563, 126.7052),
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

private fun findNearbyPopups(center: LatLng): List<PopupPlace> {
    return samplePopupPlaces
        .map { popup ->
            popup.copy(
                distanceKm = distanceKm(
                    lat1 = center.latitude,
                    lon1 = center.longitude,
                    lat2 = popup.latitude,
                    lon2 = popup.longitude
                )
            )
        }
        .filter { it.distanceKm <= 5.0 }
        .sortedBy { it.distanceKm }
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