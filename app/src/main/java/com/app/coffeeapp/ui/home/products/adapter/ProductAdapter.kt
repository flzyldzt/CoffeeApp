package com.app.coffeeapp.ui.home.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ItemProductBinding
import com.app.coffeeapp.domain.products.ProductUiModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.DecimalFormat

class ProductAdapter(
    private val onAddToCartClick: (ProductUiModel) -> Unit
) : ListAdapter<ProductUiModel, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductUiModel>() {
            override fun areItemsTheSame(
                oldItem: ProductUiModel,
                newItem: ProductUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductUiModel,
                newItem: ProductUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ProductViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

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

        holder.binding.btnAddToCart.setOnClickListener {
            onAddToCartClick(item)
        }
    }
}
