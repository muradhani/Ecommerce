package com.example.ecommerce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.databinding.ItemProductImageBinding

class ViewPagerImagerAdapter(private val images: List<String>):RecyclerView.Adapter<ViewPagerImagerAdapter.ProductImageViewHolder>() {
    inner class ProductImageViewHolder(private val binding: ItemProductImageBinding):RecyclerView.ViewHolder((binding.root)){
        fun bind(imageUrl: String) {
            // Set image url using data binding
            Glide.with(binding.root.context).load(imageUrl).into(binding.imageView)
            // This is important to refresh the UI with the new data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductImageBinding.inflate(inflater, parent, false)
        return ProductImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
            holder.bind(images[position])
    }
}