package com.morphine_coder.knowmerce.core.domain.model.use_case

import com.morphine_coder.knowmerce.core.domain.model.repository.FavoriteRepository
import com.morphine_coder.knowmerce.core.model.SearchResult
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 27.
 */
class RemoveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(item: SearchResult) {
        favoriteRepository.removeFavorite(item)
    }
}