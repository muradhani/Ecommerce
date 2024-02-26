package com.example.domain.mapper

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.Product

class ProductToProductCart:Mapper<Product,ProductCartEntitity> {
    override fun map(input: Product): ProductCartEntitity {
        return ProductCartEntitity(
            input.id,
            input.thumbnail,
            input.title,
            1,
            input.newprice
            )
    }
}