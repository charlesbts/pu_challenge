package br.com.pu.pu_challenge.data.network.remote

import com.squareup.moshi.Json

data class PartnerRemote(
    @Json(name = "name") val title : String
)