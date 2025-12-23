package com.app.coffeeapp.ui.home.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemCampaignBinding
import com.app.coffeeapp.ui.home.dashboard.model.Campaign
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CampaignAdapter(
    private val onItemClick: (Campaign) -> Unit
) : ListAdapter<Campaign, CampaignAdapter.CampaignViewHolder>(DiffCallback) {

    inner class CampaignViewHolder(
        val binding: ItemCampaignBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding = ItemCampaignBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        val item = getItem(position)

        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.imgCampaign)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Campaign>() {

            override fun areItemsTheSame(
                oldItem: Campaign,
                newItem: Campaign
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Campaign,
                newItem: Campaign
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
