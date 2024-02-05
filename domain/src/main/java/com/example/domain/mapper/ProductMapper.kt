package com.example.domain.mapper

import com.example.domain.entities.ProductEntity
import com.example.domain.models.Product
import javax.inject.Inject
import kotlin.math.abs

class ProductMapper @Inject constructor():Mapper<ProductEntity,Product> {
    override fun map(input: ProductEntity): Product {
        return Product(
            input.id,
            input.category,
            input.description,
            input.thumbnail,
            input.images,
            input.price.toDouble(),
            abs(input.price-(input.discountPercentage.toDouble()/100) * input.price.toDouble()).toInt(),
            input.discountPercentage,
            input.stock != 0,
            input.rating,
            input.title
        )
    }
}