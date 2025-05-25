package com.morphine_coder.knowmerce.core.domain.model.use_case

import com.morphine_coder.knowmerce.core.domain.model.repository.SearchRepository
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(keyword: String) = searchRepository.search(keyword)
}