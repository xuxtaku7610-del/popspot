package com.popspot.app.presentation.popup

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popspot.app.domain.model.PopupPost
import com.popspot.app.presentation.ui.components.EmptyScreen
import com.popspot.app.presentation.ui.components.ErrorScreen
import com.popspot.app.presentation.ui.components.LoadingScreen
import com.popspot.app.presentation.ui.components.PopupFeedTopBar

private val Purple = Color(0xFF7C5CBF)
private val ChipSelected = Color(0xFF7C3AED)
private val ChipBorder = Color(0xFFCCCCCC)
private val LightPurple = Color(0xFFF0EEFF)

private val FILTER_CHIPS = listOf("전체", "성수", "한정판", "캐릭터", "팝업카페")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupFeedScreen(
    navController: NavController,
    viewModel: PopupFeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedChip by viewModel.selectedChip.collectAsState()
    val filteredItems by viewModel.filteredItems.collectAsState()

    val searchFocusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            PopupFeedTopBar(
                title = "팝업 트렌드",
                onBackClick = { navController.popBackStack() },
                onSearchClick = { searchFocusRequester.requestFocus() }
            )
        },
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { newValue ->
                    // 한글/영어/숫자/공백/특수문자 전부 허용
                    viewModel.onSearch(newValue)
                },
                focusRequester = searchFocusRequester,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )

            ChipRow(
                chips = FILTER_CHIPS,
                selectedChip = selectedChip,
                onChipSelect = viewModel::onChipSelected
            )

            Spacer(modifier = Modifier.height(4.dp))

            when {
                uiState.isLoading -> {
                    LoadingScreen(modifier = Modifier.weight(1f))
                }

                uiState.error != null -> {
                    ErrorScreen(
                        message = uiState.error ?: "오류가 발생했어요.",
                        modifier = Modifier.weight(1f)
                    )
                }

                filteredItems.isEmpty() -> {
                    EmptyScreen(
                        message = "검색 결과가 없어요",
                        modifier = Modifier.weight(1f)
                    )
                }

                else -> {
                    PostList(
                        posts = filteredItems,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = query,
        onValueChange = { newValue ->
            // 여기서 영어만 남기는 filter/regex 절대 넣지 않음
            onQueryChange(newValue)
        },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = {
            Text(
                text = "검색어를 입력하세요",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(50),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    )
}

@Composable
private fun ChipRow(
    chips: List<String>,
    selectedChip: String,
    onChipSelect: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chips) { chip ->
            val selected = chip == selectedChip

            FilterChip(
                selected = selected,
                onClick = { onChipSelect(chip) },
                label = {
                    Text(
                        text = "#$chip",
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = ChipSelected,
                    selectedLabelColor = Color.White,
                    containerColor = Color.White,
                    labelColor = Color(0xFF555555)
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selected,
                    borderColor = ChipBorder,
                    selectedBorderColor = Color.Transparent,
                    borderWidth = 1.dp,
                    selectedBorderWidth = 0.dp
                )
            )
        }
    }
}

@Composable
private fun PostList(
    posts: List<PopupPost>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(posts, key = { it.id }) { post ->
            PopupPostCard(post = post)
        }
    }
}

@Composable
private fun PopupPostCard(post: PopupPost) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightPurple)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "NAVER BLOG",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Purple)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = post.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = post.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = formatPostDate(post.postDate),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                        )

                        if (post.bloggerName.isNotBlank()) {
                            Text(
                                text = post.bloggerName,
                                style = MaterialTheme.typography.labelSmall,
                                color = Purple.copy(alpha = 0.7f)
                            )
                        }
                    }

                    TextButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.link))
                            context.startActivity(intent)
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        border = BorderStroke(1.dp, Purple),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "원문보기 ↗",
                            style = MaterialTheme.typography.labelSmall,
                            color = Purple,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

private fun formatPostDate(raw: String): String {
    if (raw.length < 8) return raw

    val year = raw.substring(0, 4)
    val month = raw.substring(4, 6)
    val day = raw.substring(6, 8)

    return "$year.$month.$day"
}