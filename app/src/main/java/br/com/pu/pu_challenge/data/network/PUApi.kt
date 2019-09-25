package br.com.pu.pu_challenge.data.network

import br.com.pu.pu_challenge.data.network.remote.ResponseRemote
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface responsible to connect the app to the remote server.
 */
interface PUApi {

    @GET("deals.json")
    fun retrieveDeals(): Call<ResponseRemote>
}