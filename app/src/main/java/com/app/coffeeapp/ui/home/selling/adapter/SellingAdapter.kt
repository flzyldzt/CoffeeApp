package com.app.coffeeapp.ui.home.selling.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemSellingBinding
import com.app.coffeeapp.domain.selling.SellingUiModel
import com.app.coffeeapp.ui.home.selling.adapter.model.ProductWithFavoriteState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.DecimalFormat

class SellingAdapter(
    private val onAddToCartClick: (SellingUiModel) -> Unit,
    private val onFavoriteClick: (SellingUiModel, Boolean) -> Unit
) : ListAdapter<ProductWithFavoriteState, SellingAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductWithFavoriteState>() {
            override fun areItemsTheSame(
                oldItem: ProductWithFavoriteState,
                newItem: ProductWithFavoriteState
            ): Boolean {
                return oldItem.product.id == newItem.product.id
            }

            override fun areContentsTheSame(
                oldItem: ProductWithFavoriteState,
                newItem: ProductWithFavoriteState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ProductViewHolder(
        val binding: ItemSellingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemSellingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val itemWithState = getItem(position)
        val item = itemWithState.product
        val isFavorite = itemWithState.isFavorite

        holder.binding.tvProductName.text = item.name

        val decimalFormat = DecimalFormat("#,##0.00")
        holder.binding.tvPrice.text = "${decimalFormat.format(item.price)} ₺"

        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.imgProduct)

        holder.binding.btnAddToCart.setOnClickListener {
            onAddToCartClick(item)
        }
        
        updateFavoriteIcon(holder.binding, isFavorite)
        
        holder.binding.imgFavorite.setOnClickListener {
            val newFavoriteState = !isFavorite
            updateFavoriteIcon(holder.binding, newFavoriteState)
            onFavoriteClick(item, newFavoriteState)
        }
    }
    
    private fun updateFavoriteIcon(binding: ItemSellingBinding, isFavorite: Boolean) {
        binding.imgFavorite.setColorFilter(
            ContextCompat.getColor(
                binding.root.context,
                if (isFavorite) R.color.app_main_color else R.color.gray
            )
        )
    }
}