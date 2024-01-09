package com.example.data.mapper

import com.example.data.dto.ProductResponse
import com.example.domain.entities.Product
import com.example.domain.mapper.Mapper
import javax.inject.Inject

class ProductMapper @Inject constructor():Mapper<ProductResponse,Product> {
    override fun map(input: ProductResponse): Product {
        return Product(input.category,
            input.description,
            input.id,
            input.image,
            input.price,
            input.rating.rate.toString(),
            input.rating.count.toString(),
            input.title
        )
    }
}