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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 26.
 */
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val search = viewModel.search.collectAsLazyPagingItems()
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = keyword,
            onValueChange = viewModel::onChangeKeyword,
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
                    )
                }
            }
        }
    }
}
