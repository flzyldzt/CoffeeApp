package com.app.coffeeapp.ui.home.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemAnnouncementBinding
import com.app.coffeeapp.domain.announcements.AnnouncementsUiModel
import com.app.coffeeapp.ui.common.UiDisplayMode
import com.app.coffeeapp.util.extensions.loadImageWithProgress

class AnnouncementAdapter(
    private val uiDisplayMode: UiDisplayMode,
    private val onItemClick: (id: Int) -> Unit
) : ListAdapter<AnnouncementsUiModel, AnnouncementAdapter.AnnouncementViewHolder>(DiffCallback) {

    inner class AnnouncementViewHolder(
        val binding: ItemAnnouncementBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AnnouncementsUiModel>() {

            override fun areItemsTheSame(
                oldItem: AnnouncementsUiModel,
                newItem: AnnouncementsUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnnouncementsUiModel,
                newItem: AnnouncementsUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val binding = ItemAnnouncementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnnouncementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val item = getItem(position)

        val params = holder.itemView.layoutParams
        if (params is RecyclerView.LayoutParams) {
            params.width = when (uiDisplayMode) {
                UiDisplayMode.DASHBOARD -> {
                    holder.itemView.context.resources
                        .getDimensionPixelSize(R.dimen.announcement_dashboard_width) // 200dp
                }

                UiDisplayMode.LIST -> {
                    ViewGroup.LayoutParams.MATCH_PARENT
                }
            }
            holder.itemView.layoutParams = params
        }

        // 🔥 Ui Mode Kontrolü (Campaign ile AYNI)
        if (uiDisplayMode == UiDisplayMode.LIST) {
            holder.binding.layoutBottomInfo.visibility = View.VISIBLE
            holder.binding.tvAnnouncementTitle.text = item.title.orEmpty()
        } else {
            holder.binding.layoutBottomInfo.visibility = View.GONE
        }

        // recycle bug fix
        holder.binding.pbAnnouncement.visibility = View.VISIBLE
        holder.binding.imgAnnouncement.setImageDrawable(null)

        holder.binding.imgAnnouncement.loadImageWithProgress(
            url = item.imageUrl,
            progressBar = holder.binding.pbAnnouncement
        )

        holder.itemView.setOnClickListener {
            onItemClick(item.id)
        }
    }
}