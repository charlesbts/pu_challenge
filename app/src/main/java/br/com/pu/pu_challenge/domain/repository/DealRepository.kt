package br.com.pu.pu_challenge.domain.repository

import androidx.paging.PagedList
import br.com.pu.pu_challenge.domain.entity.Deal

/**
 * Interface to access all data source for the [Deal] entity.
 */
interface DealRepository {

    /**
     * Gets every [Deal] present in the repository.
     * @return a list of every [Deal]
     */
    fun getDeals(): PagedList<Deal>
}