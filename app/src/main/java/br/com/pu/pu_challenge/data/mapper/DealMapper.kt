package br.com.pu.pu_challenge.data.mapper

import br.com.pu.pu_challenge.data.network.remote.DealRemote
import br.com.pu.pu_challenge.domain.entity.Deal

fun DealRemote.toEntity() = Deal(this.id, this.thumbUrl, this.title,
    this.description, this.salePrice)