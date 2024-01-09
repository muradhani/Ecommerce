package com.example.ecommerce.adapters

import com.example.domain.entities.Product
import com.example.ecommerce.R
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.databinding.BestDealsRvItemBinding

class SpecialRvAdapter(
    private val list : List<Product>,
    private val productListnter: SpecialProductsListnter
): BaseRecyclerViewAdapterAdapter<BestDealsRvItemBinding, Product>(list) {
    override fun bind(binding: BestDealsRvItemBinding, item: Product) {
        binding.product = item
    }

    override fun getLayoutId(): Int {
        return R.layout.special_rv_item
    }
}
interface SpecialProductsListnter {
    fun onProductClicked(product: Product)
}