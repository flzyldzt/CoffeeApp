package com.app.coffeeapp.ui.home.stores.adapter

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
import com.app.coffeeapp.ui.home.stores.adapter.model.StoreWithFavoriteState
import java.text.DecimalFormat

class StoreAdapter(
    private val onDetailClick: (StoreUiModel) -> Unit,
    private val onFavoriteClick: (StoreUiModel, Boolean) -> Unit
) : ListAdapter<StoreWithFavoriteState, StoreAdapter.StoreViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoreWithFavoriteState>() {
            override fun areItemsTheSame(
                oldItem: StoreWithFavoriteState,
                newItem: StoreWithFavoriteState
            ): Boolean {
                return oldItem.store.id == newItem.store.id
            }

            override fun areContentsTheSame(
                oldItem: StoreWithFavoriteState,
                newItem: StoreWithFavoriteState
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
        fun bind(itemWithState: StoreWithFavoriteState) = with(binding) {
            val item = itemWithState.store
            val isFavorite = itemWithState.isFavorite
            
            tvStoreName.text = item.name
            tvAddress.text = item.address
            tvDistance.text = "${decimalFormat.format(item.distance)} km"
            tvOpeningHours.text = item.openingHours

            updateFavoriteIcon(isFavorite)
            
            root.setOnClickListener {
                onDetailClick(item)
            }
            
            imgFavorite.setOnClickListener {
                val newFavoriteState = !isFavorite
                updateFavoriteIcon(newFavoriteState)
                onFavoriteClick(item, newFavoriteState)
            }
        }
        
        private fun updateFavoriteIcon(isFavorite: Boolean) {
            binding.imgFavorite.setColorFilter(
                ContextCompat.getColor(
                    binding.root.context,
                    if (isFavorite) R.color.app_main_color else R.color.gray
                )
            )
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