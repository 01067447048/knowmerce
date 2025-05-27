package com.morphine_coder.knowmerce.feature.saved_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.morphine_coder.knowmerce.core.designsystem.components.ImageCard
import com.morphine_coder.knowmerce.core.designsystem.snackbar.SnackBarAction
import com.morphine_coder.knowmerce.core.designsystem.snackbar.SnackBarController
import com.morphine_coder.knowmerce.core.designsystem.snackbar.SnackBarEvent
import kotlinx.coroutines.launch

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 27.
 */
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favorites = viewModel.favoritePagingData.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(favorites.itemCount) { index ->
                favorites[index]?.let {
                    ImageCard(
                        imageUrl = it.imageUrl,
                        tileStamp = it.timestamp,
                        isSavedImage = it.isFavorite,
                        onClick = {
                            scope.launch {
                                viewModel.removeFavorite(it)
                                SnackBarController.sendEvent(
                                    event = SnackBarEvent(
                                        message = "즐겨찾기에서 제거되었습니다.",
                                        action = SnackBarAction(
                                            name = "실행 취소",
                                            action = { viewModel.undoRemoveFavorite() }
                                        )
                                    )
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}