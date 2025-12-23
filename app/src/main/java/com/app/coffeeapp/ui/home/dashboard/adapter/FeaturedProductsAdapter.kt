package com.app.coffeeapp.ui.home.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemFeaturedProductsBinding
import com.app.coffeeapp.ui.home.dashboard.model.FeaturedProducts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FeaturedProductsAdapter :
    ListAdapter<FeaturedProducts, FeaturedProductsAdapter.FeaturedViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FeaturedProducts>() {

            override fun areItemsTheSame(
                oldItem: FeaturedProducts,
                newItem: FeaturedProducts
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FeaturedProducts,
                newItem: FeaturedProducts
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FeaturedViewHolder(
        val binding: ItemFeaturedProductsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val binding = ItemFeaturedProductsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FeaturedViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FeaturedViewHolder,
        position: Int
    ) {
        val item = getItem(position)

        holder.binding.tvTitle.text = item.title

        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.imgIcon)
    }
}