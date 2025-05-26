package com.morphine_coder.knowmerce.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.morphine_coder.knowmerce.core.common.Logg
import com.morphine_coder.knowmerce.core.domain.model.use_case.SearchUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
): ViewModel() {

    private var searchJob: Job? = null

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private val _search = MutableStateFlow<PagingData<SearchResult>>(PagingData.empty())
    val search = _search.asStateFlow()

    fun onChangeKeyword(newKeyword: String) {
        _keyword.value = newKeyword
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400L)
            searchUseCase(newKeyword)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _search.value = it
                }
        }
    }
}