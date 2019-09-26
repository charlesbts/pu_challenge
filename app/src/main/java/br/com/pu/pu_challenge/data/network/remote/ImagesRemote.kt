package br.com.pu.pu_challenge.data.network.remote

import com.squareup.moshi.Json

data class ImagesRemote(
    @Json(name = "image") val thumbUrl : String
)