package com.example.ecommerce.adapters

import com.example.domain.models.Product
import com.example.ecommerce.R
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.databinding.BestDealsRvItemBinding
import com.example.ecommerce.databinding.SpecialRvItemBinding

class SpecialRvAdapter(
    private val list : List<Product>,
    private val productListnter: SpecialProductsListnter
): BaseRecyclerViewAdapterAdapter<SpecialRvItemBinding, Product>(list) {
    override fun bind(binding: SpecialRvItemBinding, item: Product) {
        binding.product = item
    }

    override fun getLayoutId(): Int {
        return R.layout.special_rv_item
    }
}
interface SpecialProductsListnter {
    fun onProductClicked(product: Product)
}