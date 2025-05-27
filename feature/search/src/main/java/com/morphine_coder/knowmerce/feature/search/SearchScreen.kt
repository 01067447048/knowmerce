package com.morphine_coder.knowmerce.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.morphine_coder.knowmerce.core.designsystem.components.ImageCard
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 26.
 */
@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val search = viewModel.search.collectAsLazyPagingItems()
    var keyword by remember {
        mutableStateOf("")
    }

    LaunchedEffect(keyword) {
        snapshotFlow { keyword }
            .debounce(350L)
            .distinctUntilChanged()
            .collectLatest {
                if (it.isNotEmpty()) {
                    viewModel.search(it)
                } else {
                    viewModel.clearSearchResults()
                }
            }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.navigateToFavorite()
                },
                modifier = Modifier
                    .padding(4.dp),
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorite Icon",
                    tint = Color.Yellow,
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = keyword,
                onValueChange = {
                    keyword = it
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(
                    corner = CornerSize(8.dp)
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                viewModel
                            }
                    )
                },
                label = {
                    Text("검색어 입력")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
            ) // Search TextField

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp, end = 8.dp
                    )
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(search.itemCount) { index ->
                    search[index]?.let {
                        ImageCard(
                            imageUrl = it.imageUrl,
                            tileStamp = it.timestamp,
                            isSavedImage = it.isFavorite,
                            onClick = {
                                viewModel.toggleFavorite(it)
                            }
                        )
                    }
                }
            }
        }
    }
}
