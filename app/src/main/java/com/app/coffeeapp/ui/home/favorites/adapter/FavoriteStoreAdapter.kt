package com.app.coffeeapp.ui.home.favorites.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemStoreBinding
import com.app.coffeeapp.domain.stores.StoreUiModel
import java.text.DecimalFormat

class FavoriteStoreAdapter(
    private val onStoreClick: (Int) -> Unit,
    private val onFavoriteClick: (Int) -> Unit
) : ListAdapter<StoreUiModel, FavoriteStoreAdapter.StoreViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoreUiModel>() {
            override fun areItemsTheSame(
                oldItem: StoreUiModel,
                newItem: StoreUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StoreUiModel,
                newItem: StoreUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class StoreViewHolder(
        private val binding: ItemStoreBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val decimalFormat = DecimalFormat("#,##0.00")

        @SuppressLint("SetTextI18n")
        fun bind(item: StoreUiModel) = with(binding) {
            tvStoreName.text = item.name
            tvAddress.text = item.address
            tvDistance.text = "${decimalFormat.format(item.distance)} km"
            tvOpeningHours.text = item.openingHours

            // Set favorite icon as filled
            imgFavorite.setColorFilter(
                ContextCompat.getColor(root.context, R.color.app_main_color)
            )

            root.setOnClickListener {
                onStoreClick(item.id)
            }

            imgFavorite.setOnClickListener {
                onFavoriteClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

