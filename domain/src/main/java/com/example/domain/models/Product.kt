package com.example.domain.models

data class Product(
    val id: Int,
    val category: String,
    val description: String,
    val thumbnail: String,
    val images: List<String>,
    val oldprice: Double,
    val newprice: Int,
    val discountPercentage: Double,
    val available: Boolean,
    val rating: Double,
    val title: String
)
