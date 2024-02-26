package com.example.domain.entities

data class CartItems(
    val items : MutableList<ProductCartEntitity>,
    val totalPrice:Int
)
