package br.com.pu.pu_challenge.data.network.remote

import com.squareup.moshi.Json

data class ResponseRemote(
    @Json(name = "code") val code: Int,
    @Json(name = "response") val deals: List<DealRemote>
)