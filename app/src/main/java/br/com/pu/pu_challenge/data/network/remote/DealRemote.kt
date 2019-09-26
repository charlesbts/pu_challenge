package br.com.pu.pu_challenge.data.network.remote

import com.squareup.moshi.Json

data class DealRemote(
    @Json(name = "deal_id") val id: String,
    @Json(name = "images") val images: List<ImagesRemote>,
    @Json(name = "partner") val partner: PartnerRemote,
    @Json(name = "short_title") val description: String,
    @Json(name = "sale_price") val salePrice: Float
)