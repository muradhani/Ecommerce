package com.example.data.dto.newApiDto


import com.example.domain.entities.ProductEntity

data class CategoryProductsResponse(
    val limit: Int,
    val products: List<ProductEntity>,
    val skip: Int,
    val total: Int
)