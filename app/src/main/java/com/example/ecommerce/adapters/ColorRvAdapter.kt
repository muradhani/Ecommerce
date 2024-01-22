package com.example.ecommerce.adapters

import com.example.domain.entities.Product
import com.example.ecommerce.R
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.databinding.SpecialRvItemBinding

class ColorRvAdapter(
    private val list : List<Product>,
    private val ColorsListnter: SpecialProductsListnter
): BaseRecyclerViewAdapterAdapter<SpecialRvItemBinding, Product>(list) {
    override fun bind(binding: SpecialRvItemBinding, item: Product) {
        binding.product = item
    }

    override fun getLayoutId(): Int {
        return R.layout.special_rv_item
    }
}
interface ColorsListnter {
    fun onColorsClicked(product: Product)
}