package com.popspot.app.presentation.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.domain.model.Event
import com.popspot.app.presentation.ui.components.EmptyScreen
import com.popspot.app.presentation.ui.components.ErrorScreen
import com.popspot.app.presentation.ui.components.LoadingScreen
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val CITY_OPTIONS = listOf("서울", "부산", "인천", "대전", "대구")

// ─── Entry point ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(
    navController: NavController,
    viewModel: EventListViewModel = hiltViewModel()
) {
    val uiState      by viewModel.uiState.collectAsState()
    val events       by viewModel.events.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()

    Scaffold(
        topBar = { EventListTopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            // ── Filter row ────────────────────────────────────────────────
            FilterRow(
                selectedCity     = selectedCity,
                selectedDate     = selectedDate,
                onCitySelected   = viewModel::filterByCity,
                onDateSelected   = viewModel::filterByDate,
                onResetFilters   = {
                    viewModel.filterByCity("서울")
                    viewModel.filterByDate(null)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )

            // ── Content ───────────────────────────────────────────────────
            when {
                uiState.isLoading ->
                    LoadingScreen(modifier = Modifier.weight(1f))

                uiState.error != null ->
                    ErrorScreen(
                        message  = uiState.error ?: "",
                        modifier = Modifier.weight(1f)
                    )

                events.isEmpty() ->
                    EmptyScreen(
                        message  = "조건에 맞는 행사가 없어요",
                        modifier = Modifier.weight(1f)
                    )

                else ->
                    EventGrid(
                        events       = events,
                        onCardClick  = { event ->
                            navController.navigate("detail/${event.id}")
                        },
                        modifier = Modifier.weight(1f)
                    )
            }
        }
    }
}

// ─── Top App Bar ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventListTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text       = "공식 행사",
                // 수정: 홈 스타일 통일 — Bold → SemiBold (letterSpacing은 titleLarge 스타일에서 자동 적용)
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            // Search icon — placeholder for future text-search feature
            IconButton(onClick = { /* TODO: text search */ }) {
                Icon(
                    imageVector        = Icons.Default.Search,
                    contentDescription = "검색"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        // 수정: 홈 스타일 통일 — 상단 여백 14dp로 축소
        windowInsets = WindowInsets(top = 14.dp)
    )
}

// ─── Filter row ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterRow(
    selectedCity: String,
    selectedDate: String?,
    onCitySelected: (String) -> Unit,
    onDateSelected: (String?) -> Unit,
    onResetFilters: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment     = Alignment.CenterVertically
    ) {
        // ── City dropdown ─────────────────────────────────────────────────
        CityDropdown(
            selectedCity   = selectedCity,
            onCitySelected = onCitySelected
        )

        // ── Date picker ───────────────────────────────────────────────────
        DateDropdownButton(
            selectedDate   = selectedDate,
            onDateSelected = onDateSelected
        )

        Spacer(modifier = Modifier.weight(1f))

        // ── Reset button ──────────────────────────────────────────────────
        OutlinedButton(
            onClick      = onResetFilters,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
            shape        = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector        = Icons.Default.Tune,
                contentDescription = null,
                modifier           = Modifier.size(14.dp)
            )
            Text(
                text     = " 전체 필터",
                style    = MaterialTheme.typography.labelMedium
            )
        }
    }
}

// ─── City dropdown ────────────────────────────────────────────────────────────

@Composable
private fun CityDropdown(
    selectedCity: String,
    onCitySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(
            onClick        = { expanded = true },
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            shape          = RoundedCornerShape(8.dp)
        ) {
            Text(
                text  = selectedCity,
                style = MaterialTheme.typography.labelMedium
            )
            Icon(
                imageVector        = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier           = Modifier.size(16.dp)
            )
        }

        DropdownMenu(
            expanded         = expanded,
            onDismissRequest = { expanded = false }
        ) {
            CITY_OPTIONS.forEach { city ->
                DropdownMenuItem(
                    text    = { Text(text = city) },
                    onClick = {
                        onCitySelected(city)
                        expanded = false
                    }
                )
            }
        }
    }
}

// ─── Date picker button ───────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateDropdownButton(
    selectedDate: String?,
    onDateSelected: (String?) -> Unit
) {
    var showPicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    // Display "날짜 선택" when no date is set, "MM.dd" when one is selected
    val label = selectedDate?.let { formatDateLabel(it) } ?: "날짜 선택"

    OutlinedButton(
        onClick        = { showPicker = true },
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        shape          = RoundedCornerShape(8.dp),
        colors = if (selectedDate != null)
            ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        else ButtonDefaults.outlinedButtonColors()
    ) {
        Icon(
            imageVector        = Icons.Default.CalendarMonth,
            contentDescription = null,
            modifier           = Modifier.size(14.dp)
        )
        Text(
            text     = " $label",
            style    = MaterialTheme.typography.labelMedium
        )
        Icon(
            imageVector        = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier           = Modifier.size(16.dp)
        )
    }

    if (showPicker) {
        DatePickerDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis
                        onDateSelected(millis?.let { millisToYyyyMmDd(it) })
                        showPicker = false
                    }
                ) { Text("확인") }
            },
            dismissButton = {
                TextButton(onClick = { showPicker = false }) { Text("취소") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

// ─── Event grid ───────────────────────────────────────────────────────────────

@Composable
private fun EventGrid(
    events: List<Event>,
    onCardClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns             = GridCells.Fixed(2),
        modifier            = modifier,
        contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement   = Arrangement.spacedBy(12.dp)
    ) {
        items(events, key = { it.id }) { event ->
            EventCard(
                event   = event,
                onClick = { onCardClick(event) }
            )
        }
    }
}

// ─── Event card ───────────────────────────────────────────────────────────────

@Composable
private fun EventCard(
    event: Event,
    onClick: () -> Unit
) {
    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape     = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // ── Thumbnail area (16:9 ratio) ───────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint               = MaterialTheme.colorScheme.tertiary,
                    modifier           = Modifier.size(32.dp)
                )

                // "공식 데이터" badge — anchored to bottom-start of thumbnail
                Text(
                    text     = "공식 데이터",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    style      = MaterialTheme.typography.labelSmall,
                    color      = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // ── Text content ──────────────────────────────────────────────
            Column(modifier = Modifier.padding(10.dp)) {
                // Title — two-line clamp keeps all cards the same height
                Text(
                    text       = event.title,
                    style      = MaterialTheme.typography.bodySmall,
                    // 수정: 홈 스타일 통일 — Bold → SemiBold
                    fontWeight = FontWeight.SemiBold,
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis,
                    modifier   = Modifier.height(36.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Date range — "MM.dd ~ MM.dd"
                Text(
                    text   = formatDateRange(event.startDate, event.endDate),
                    style  = MaterialTheme.typography.labelSmall,
                    color  = MaterialTheme.colorScheme.primary,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Place with pin icon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector        = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint               = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
                        modifier           = Modifier.size(12.dp)
                    )
                    Text(
                        text     = event.place,
                        style    = MaterialTheme.typography.labelSmall,
                        color    = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

// ─── Helpers ─────────────────────────────────────────────────────────────────

// "yyyyMMdd" → "MM.dd"
private fun formatDateLabel(yyyyMmDd: String): String {
    if (yyyyMmDd.length < 8) return yyyyMmDd
    return "${yyyyMmDd.substring(4, 6)}.${yyyyMmDd.substring(6, 8)}"
}

// "yyyyMMdd" + "yyyyMMdd" → "MM.dd ~ MM.dd"
private fun formatDateRange(start: String, end: String): String {
    val s = if (start.length >= 8) "${start.substring(4, 6)}.${start.substring(6, 8)}" else start
    val e = if (end.length >= 8) "${end.substring(4, 6)}.${end.substring(6, 8)}" else end
    return "$s ~ $e"
}

// Epoch millis (UTC) → "yyyyMMdd" using device time-zone
private fun millisToYyyyMmDd(millis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(formatter)
}
