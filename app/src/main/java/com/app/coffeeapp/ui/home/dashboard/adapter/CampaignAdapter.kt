package com.app.coffeeapp.ui.home.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.databinding.ItemCampaignBinding
import com.app.coffeeapp.domain.campaigns.CampaignsUiModel
import com.app.coffeeapp.ui.common.UiDisplayMode
import com.app.coffeeapp.util.extensions.loadImageWithProgress

class CampaignAdapter(
    private val uiMode: UiDisplayMode,
    private val onItemClick: (id: Int) -> Unit
) : ListAdapter<CampaignsUiModel, CampaignAdapter.CampaignViewHolder>(DiffCallback) {

    inner class CampaignViewHolder(
        val binding: ItemCampaignBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CampaignsUiModel>() {

            override fun areItemsTheSame(
                oldItem: CampaignsUiModel,
                newItem: CampaignsUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CampaignsUiModel,
                newItem: CampaignsUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

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

        holder.binding.pbCampaign.visibility = View.VISIBLE
        holder.binding.imgCampaign.setImageDrawable(null)

        holder.binding.imgCampaign.loadImageWithProgress(
            url = item.imageUrl,
            progressBar = holder.binding.pbCampaign
        )

        holder.itemView.setOnClickListener {
            onItemClick(item.id)
        }

        if (uiMode == UiDisplayMode.LIST) {
            holder.binding.layoutBottomInfo.visibility = View.VISIBLE
            holder.binding.tvCampaignTitle.text = item.title
        } else {
            holder.binding.layoutBottomInfo.visibility = View.GONE
        }

        holder.binding.root.setOnClickListener {
            onItemClick(item.id)
        }
    }
}