package com.morphine_coder.knowmerce.feature.saved_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.cachedIn
import com.morphine_coder.knowmerce.core.domain.model.use_case.AddFavoriteUseCase
import com.morphine_coder.knowmerce.core.domain.model.use_case.GetFavoritePagingDataUseCase
import com.morphine_coder.knowmerce.core.domain.model.use_case.RemoveFavoriteUseCase
import com.morphine_coder.knowmerce.core.model.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 27.
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritePagingDataUseCase: GetFavoritePagingDataUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
): ViewModel() {

    val favoritePagingData = getFavoritePagingDataUseCase
        .invoke()
        .cachedIn(viewModelScope)

    private var lastRemovedItem: SearchResult? = null

    fun removeFavorite(item: SearchResult) {
        viewModelScope.launch {
            lastRemovedItem = item
            removeFavoriteUseCase.invoke(item)
        }
    }

    fun undoRemoveFavorite() {
        lastRemovedItem?.let { item ->
            viewModelScope.launch {
                addFavoriteUseCase.invoke(item)
                lastRemovedItem = null
            }
        }
    }
}