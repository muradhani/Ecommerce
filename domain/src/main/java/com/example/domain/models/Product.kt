package com.example.domain.models

data class Product(
    val id: Int = 0,
    val category: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val images: List<String> = emptyList(),
    val oldprice: Double = 0.0,
    val newprice: Int = 0,
    val discountPercentage: Double = 0.0,
    val available: Boolean = false,
    val rating: Double = 0.0,
    val title: String = ""
)
