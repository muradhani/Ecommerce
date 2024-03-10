package com.example.ecommerce.adapters

import android.util.Log
import com.example.domain.entities.ProductCartEntitity
import com.example.ecommerce.R
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.databinding.CartProductItemBinding

class CartRvAdapter(
    private var list: MutableList<ProductCartEntitity>,
    private val productsListnter: ProductsCartListnter
) : BaseRecyclerViewAdapterAdapter<CartProductItemBinding, ProductCartEntitity>(list) {
    override fun bind(binding: CartProductItemBinding, item: ProductCartEntitity) {
        val product = item
        binding.product = product
        binding.root.setOnClickListener {
            productsListnter.onProductClicked(product = item)
        }
        binding.imageMinus.setOnClickListener {
            productsListnter.onBtnMinusClicked(product)
//            if (product.count >= 0) {
//                var oldIndex = list.indexOf(product)
//                product.count--
//                list[oldIndex] = product
//                setData(list)
//            }
        }
        binding.imagePlus.setOnClickListener {
            productsListnter.onBtnPlusClicked(product)
//            Log.i("main",list.toString())
//            var oldIndex = list.indexOfFirst { it.id == product.id }
//            product.count++
//            list[oldIndex] = product
//            setData(list)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.cart_product_item
    }
}

interface ProductsCartListnter {
    fun onProductClicked(product: ProductCartEntitity)
    fun onBtnPlusClicked(product: ProductCartEntitity)
    fun onBtnMinusClicked(product: ProductCartEntitity)
}