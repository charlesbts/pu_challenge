package br.com.pu.pu_challenge.domain.usecase

import androidx.paging.PagedList
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.repository.DealRepository


/**
 * Get a paged list of the repository and returns for the UI layer.
 */
class GetDealsUseCase(private val repository: DealRepository): UseCase<PagedList<Deal>>() {

    override fun run() = repository.getDeals()
}