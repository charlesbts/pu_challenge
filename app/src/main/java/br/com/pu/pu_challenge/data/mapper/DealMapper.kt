package br.com.pu.pu_challenge.data.mapper

import br.com.pu.pu_challenge.data.database.local.DealLocal
import br.com.pu.pu_challenge.data.network.remote.DealRemote
import br.com.pu.pu_challenge.domain.entity.Deal

fun DealRemote.toEntity() = Deal(this.id, this.images.map { it.thumbUrl }, this.partner.title,
    this.description, this.salePrice)

fun DealRemote.toLocal() = DealLocal(
    this.id, this.images.map { it.thumbUrl }, this.partner.title,
    this.description, this.salePrice
)

fun DealLocal.toEntity() = Deal(this.id, this.thumbUrls, this.title, this.description, this.salePrice)