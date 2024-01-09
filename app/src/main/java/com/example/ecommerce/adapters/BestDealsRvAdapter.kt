package com.example.ecommerce.adapters

import com.example.domain.entities.Product
import com.example.ecommerce.R
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.databinding.BestDealsRvItemBinding

class BestDealsRvAdapter(
    private val list : List<Product>,
    private val productListnter: BestDealsProductListnter
):BaseRecyclerViewAdapterAdapter<BestDealsRvItemBinding,Product>(list) {
    override fun bind(binding: BestDealsRvItemBinding, item: Product) {
       binding.product = item
        binding.btnSeeProduct.setOnClickListener {

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.best_deals_rv_item
    }
}
interface BestDealsProductListnter {
    fun onProductClicked(product: Product)
}