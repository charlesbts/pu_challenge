package br.com.pu.pu_challenge.data

import androidx.paging.PagedList
import br.com.pu.pu_challenge.data.database.AppDatabase
import br.com.pu.pu_challenge.data.executors.BackgroundExecutor
import br.com.pu.pu_challenge.data.executors.MainThreadExecutor
import br.com.pu.pu_challenge.data.mapper.toEntity
import br.com.pu.pu_challenge.data.mapper.toLocal
import br.com.pu.pu_challenge.data.network.PUApi
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.repository.DealRepository
import java.io.IOException

const val PAGE_SIZE = 5

/**
 * Implementation of the [DealRepository], which collect the data from its sources.
 * @property api the interface for the server remote connection
 */
class DealRepositoryImpl(private val api: PUApi, private val db: AppDatabase) : DealRepository {

    override fun getDeals(): PagedList<Deal> {
        val call = api.retrieveDeals()

        val response = call.execute()

        return try {
            if (response.isSuccessful) {
                val dealsRemote = response.body()!!.deals
                val dealsLocal = dealsRemote.map { it.toLocal() }
                db.dealDao().insertDeals(dealsLocal)
                val ds = db.dealDao().retrieveDeals().map{it.toEntity()}.create()

                val config = PagedList.Config.Builder()
                    .setInitialLoadSizeHint(PAGE_SIZE)
                    .setPageSize(PAGE_SIZE)
                    .build()

                PagedList.Builder<Int, Deal>(ds, config)
                    .setFetchExecutor{ BackgroundExecutor() }
                    .setNotifyExecutor{ MainThreadExecutor() }
                    .build()

            } else {
                throw IOException()
            }
        } catch (ioException: IOException) {
            throw ioException
        }
    }
}