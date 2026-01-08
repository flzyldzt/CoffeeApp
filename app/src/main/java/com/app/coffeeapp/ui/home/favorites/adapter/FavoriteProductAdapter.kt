package com.app.coffeeapp.ui.home.favorites.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemSellingBinding
import com.app.coffeeapp.domain.selling.SellingUiModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.DecimalFormat

class FavoriteProductAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onFavoriteClick: (Int) -> Unit
) : ListAdapter<SellingUiModel, FavoriteProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SellingUiModel>() {
            override fun areItemsTheSame(
                oldItem: SellingUiModel,
                newItem: SellingUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SellingUiModel,
                newItem: SellingUiModel
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
        val item = getItem(position)

        holder.binding.tvProductName.text = item.name

        val decimalFormat = DecimalFormat("#,##0.00")
        holder.binding.tvPrice.text = "${decimalFormat.format(item.price)} ₺"

        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.imgProduct)

        holder.binding.imgFavorite.setColorFilter(
            ContextCompat.getColor(holder.itemView.context, R.color.app_main_color)
        )

        holder.binding.root.setOnClickListener {
            onProductClick(item.id)
        }

        holder.binding.imgFavorite.setOnClickListener {
            onFavoriteClick(item.id)
        }

        holder.binding.btnAddToCart.visibility = ViewGroup.GONE
    }
}