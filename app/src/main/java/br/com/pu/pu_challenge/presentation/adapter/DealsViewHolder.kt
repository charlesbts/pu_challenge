package br.com.pu.pu_challenge.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pu.pu_challenge.R
import br.com.pu.pu_challenge.domain.entity.Deal
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_deals.view.*
import java.util.*

class DealsViewHolder(override val containerView: View, private val context: Context) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindTo(deal: Deal, listener: (Deal) -> Unit) {
        deal.let {
            Glide.with(context)
                .load(it.thumbUrls[0])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(containerView.dealImageView)
            containerView.dealTitleTextView.text = it.title
            containerView.dealDescriptionTextView.text = it.description
            containerView.dealPriceTextView.text =
                String.format(Locale("pt", "BR"), "%.2f", it.salePrice)
            containerView.setOnClickListener{ listener.invoke(deal) }
        }
    }

    fun clear() {
        containerView.dealImageView.setImageResource(0)
        containerView.dealTitleTextView.text = ""
        containerView.dealDescriptionTextView.text = ""
        containerView.dealPriceTextView.text = ""
    }

    companion object {
        fun create(parent: ViewGroup, context: Context): DealsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_deals, parent, false)
            return DealsViewHolder(view, context)
        }
    }
}