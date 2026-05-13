package com.popspot.app.presentation.popup

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.domain.model.PopupPost
import com.popspot.app.presentation.ui.components.EmptyScreen
import com.popspot.app.presentation.ui.components.ErrorScreen
import com.popspot.app.presentation.ui.components.LoadingScreen

// Filter chips shown below the search bar
private val FILTER_CHIPS = listOf("전체", "성수", "한정판", "캐릭터", "팝업카페")

// ─── Entry point ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupFeedScreen(
    navController: NavController,
    viewModel: PopupFeedViewModel = hiltViewModel()
) {
    val uiState       by viewModel.uiState.collectAsState()
    val searchQuery   by viewModel.searchQuery.collectAsState()
    val selectedChip  by viewModel.selectedChip.collectAsState()
    val filteredItems by viewModel.filteredItems.collectAsState()

    // Shared FocusRequester — search icon in TopAppBar triggers focus on the field below
    val searchFocusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            PopupFeedTopBar(
                onBack         = { navController.popBackStack() },
                onSearchClick  = { searchFocusRequester.requestFocus() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            // ── Search bar ────────────────────────────────────────────────
            SearchBar(
                query            = searchQuery,
                onQueryChange    = viewModel::onSearch,
                focusRequester   = searchFocusRequester,
                modifier         = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )

            // ── Chip row ──────────────────────────────────────────────────
            ChipRow(
                chips        = FILTER_CHIPS,
                selectedChip = selectedChip,
                onChipSelect = viewModel::onChipSelected
            )

            Spacer(modifier = Modifier.height(4.dp))

            // ── Content area ──────────────────────────────────────────────
            when {
                uiState.isLoading ->
                    LoadingScreen(modifier = Modifier.weight(1f))

                uiState.error != null ->
                    ErrorScreen(
                        message  = uiState.error ?: "",
                        modifier = Modifier.weight(1f)
                    )

                filteredItems.isEmpty() ->
                    EmptyScreen(
                        message  = "검색 결과가 없어요",
                        modifier = Modifier.weight(1f)
                    )

                else ->
                    PostList(
                        posts    = filteredItems,
                        modifier = Modifier.weight(1f)
                    )
            }
        }
    }
}

// ─── Top App Bar ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopupFeedTopBar(
    onBack: () -> Unit,
    onSearchClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기"
                )
            }
        },
        title = {
            Text(
                text       = "팝업 트렌드",
                fontWeight = FontWeight.Bold,
                fontSize   = 18.sp
            )
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector        = Icons.Default.Search,
                    contentDescription = "검색"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

// ─── Search bar ───────────────────────────────────────────────────────────────

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value         = query,
        onValueChange = onQueryChange,
        modifier      = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder   = {
            Text(
                text  = "검색어를 입력하세요",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector        = Icons.Default.Search,
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        },
        singleLine    = true,
        shape         = RoundedCornerShape(50),   // pill shape
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    )
}

// ─── Filter chip row ──────────────────────────────────────────────────────────

@Composable
private fun ChipRow(
    chips: List<String>,
    selectedChip: String,
    onChipSelect: (String) -> Unit
) {
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chips) { chip ->
            FilterChip(
                selected  = chip == selectedChip,
                onClick   = { onChipSelect(chip) },
                label     = { Text(text = "#$chip") },
                colors    = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor     = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

// ─── Post list ────────────────────────────────────────────────────────────────

@Composable
private fun PostList(
    posts: List<PopupPost>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier           = modifier,
        contentPadding     = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(posts, key = { it.id }) { post ->
            PopupPostCard(post = post)
        }
    }
}

// ─── Post card ────────────────────────────────────────────────────────────────

@Composable
private fun PopupPostCard(post: PopupPost) {
    val context = LocalContext.current

    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            // ── Thumbnail placeholder (80 × 80dp, TealWave tint) ─────────
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = Icons.Default.Article,
                    contentDescription = null,
                    tint               = MaterialTheme.colorScheme.tertiary,
                    modifier           = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // ── Text content + NAVER badge + CTA ─────────────────────────
            Column(modifier = Modifier.weight(1f)) {
                // NAVER BLOG badge
                Text(
                    text     = "NAVER BLOG",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    style    = MaterialTheme.typography.labelSmall,
                    color    = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Title — bold, one-line clamp
                Text(
                    text       = post.title,
                    style      = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Description — two-line clamp
                Text(
                    text     = post.description,
                    style    = MaterialTheme.typography.bodySmall,
                    color    = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    // Date — "yyyyMMdd" → "yyyy.MM.dd"
                    Text(
                        text  = formatPostDate(post.postDate),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                    )

                    // "원문 보기 ↗" — opens blog URL in external browser
                    TextButton(
                        onClick      = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.link))
                            context.startActivity(intent)
                        },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text(
                            text  = "원문 보기 ↗",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

// ─── Helpers ─────────────────────────────────────────────────────────────────

// Converts "yyyyMMdd" → "yyyy.MM.dd"; falls back to raw if unexpected length
private fun formatPostDate(raw: String): String {
    if (raw.length < 8) return raw
    val year  = raw.substring(0, 4)
    val month = raw.substring(4, 6)
    val day   = raw.substring(6, 8)
    return "$year.$month.$day"
}
