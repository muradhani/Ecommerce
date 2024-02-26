package com.example.domain.entities

data class CartItems(
    val items : List<ProductCartEntitity>,
    val totalPrice:Int
)
