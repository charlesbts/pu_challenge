package br.com.pu.pu_challenge.presentation

import android.os.Bundle
import androidx.core.os.bundleOf
import br.com.pu.pu_challenge.domain.entity.Deal

const val DEAL_ID = "id"
const val DEAL_THUMBS = "thumbs"
const val DEAL_TITLE = "title"
const val DEAL_DESCRIPTION = "description"
const val DEAL_PRICE = "price"

fun Deal.toBundle() = bundleOf(
    DEAL_ID to this.id,
    DEAL_THUMBS to this.thumbUrls,
    DEAL_TITLE to this.title,
    DEAL_DESCRIPTION to this.description,
    DEAL_PRICE to this.salePrice
)

fun Bundle.toEntity() = Deal (
    getString(DEAL_ID)!!,
    getStringArrayList(DEAL_THUMBS)!!,
    getString(DEAL_TITLE)!!,
    getString(DEAL_DESCRIPTION)!!,
    getFloat(DEAL_PRICE)
)
