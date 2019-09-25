package br.com.pu.pu_challenge.domain.entity

data class Deal(
    val id: String,
    val thumbUrl: String,
    val title: String,
    val description: String,
    val salePrice: Float
)