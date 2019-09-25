package br.com.pu.pu_challenge.data

import br.com.pu.pu_challenge.data.mapper.toEntity
import br.com.pu.pu_challenge.data.network.PUApi
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.repository.DealRepository
import java.io.IOException

/**
 * Implementation of the [DealRepository], which collect the data from its sources.
 * @property api the interface for the server remote connection
 */
class DealRepositoryImpl(private val api: PUApi) : DealRepository {

    override fun getDeals(): List<Deal> {
        val call = api.retrieveDeals()

        val response = call.execute()

        return try {
            if (response.isSuccessful) {
                val dealsRemote = response.body()!!.deals
                dealsRemote.map { it.toEntity() }
            } else {
                throw IOException()
            }
        } catch (ioException: IOException) {
            throw ioException
        }
    }
}