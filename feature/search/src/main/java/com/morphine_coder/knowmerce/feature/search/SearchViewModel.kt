package com.morphine_coder.knowmerce.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.morphine_coder.knowmerce.core.common.Logg
import com.morphine_coder.knowmerce.core.domain.model.use_case.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase,
): ViewModel() {

    fun search(keyword: String) {
        viewModelScope.launch {
            useCase.invoke(keyword)
                .cachedIn(viewModelScope)
                .collectLatest { result ->
                    Logg.d("Search results for '$keyword': ${result.map { it.imageUrl }}")
                }
        }

    }
}