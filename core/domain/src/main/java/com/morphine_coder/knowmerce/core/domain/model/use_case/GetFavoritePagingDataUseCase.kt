package com.morphine_coder.knowmerce.core.domain.model.use_case

import com.morphine_coder.knowmerce.core.domain.model.repository.FavoriteRepository
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 27.
 */
class GetFavoritePagingDataUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke() = favoriteRepository.getFavoritePaging()

}