package com.morphine_coder.knowmerce.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.morphine_coder.knowmerce.core.common.Logg
import com.morphine_coder.knowmerce.core.common.navigation.Destination
import com.morphine_coder.knowmerce.core.common.navigation.Navigator
import com.morphine_coder.knowmerce.core.domain.model.use_case.SearchUseCase
import com.morphine_coder.knowmerce.core.domain.model.use_case.ToggleFavoriteUseCase
import com.morphine_coder.knowmerce.core.model.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val navigator: Navigator
): ViewModel() {

    private var searchJob: Job? = null
    private var currentKeyword: String? = null

    private val _search = MutableStateFlow<PagingData<SearchResult>>(PagingData.empty())
    val search = _search.asStateFlow()

    fun search(keyword: String) {
        if (keyword == currentKeyword) return  // 동일한 키워드면 무시
        currentKeyword = keyword

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchUseCase(keyword)
                .cachedIn(viewModelScope)
                .collectLatest { _search.value = it }
        }
    }

    fun toggleFavorite(item: SearchResult) {
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(item)
            _search.update { oldPagingData ->
                oldPagingData.map { current ->
                    if (current.docUrl == item.docUrl) {
                        current.copy(isFavorite = !current.isFavorite)
                    } else {
                        current
                    }
                }
            }
        }
    }

    fun clearSearchResults() {
        currentKeyword = null
        searchJob?.cancel()
        _search.value = PagingData.empty()
    }

    fun navigateToFavorite() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.FavoriteRoute,
                navOptions = {
                    popUpTo(Destination.SearchRoute) {
                        saveState = true
                    }
                }
            )
        }
    }
}