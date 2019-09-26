package br.com.pu.pu_challenge.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.pu.pu_challenge.domain.entity.Deal

class DealsAdapter(private val context: Context, private val listener: (Deal) -> Unit) :
    PagedListAdapter<Deal, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DealsViewHolder.create(parent, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("KEYYP", position.toString())
        val item : Deal? = getItem(position)
        if (item != null) {
            (holder as DealsViewHolder).bindTo(item, listener)
        } else {
            (holder as DealsViewHolder).clear()
            Log.d("KEYYYY", "NULLLLL")
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Deal>() {

            override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean =
                oldItem == newItem
        }
    }
}