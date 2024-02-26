package com.example.ecommerce.adapters

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.Product
import com.example.ecommerce.R
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.databinding.CartProductItemBinding

class CartRvAdapter(
    private var list :List<ProductCartEntitity>,
    private val productsListnter: ProductsCartListnter
):BaseRecyclerViewAdapterAdapter<CartProductItemBinding,ProductCartEntitity>(list) {
    override fun bind(binding: CartProductItemBinding, item: ProductCartEntitity) {
        binding.product = item
        binding.root.setOnClickListener {
            productsListnter.onProductClicked(product = item)
        }
    }

    override fun getLayoutId(): Int {
       return R.layout.cart_product_item
    }
}

interface ProductsCartListnter {
    fun onProductClicked(product: ProductCartEntitity)
}