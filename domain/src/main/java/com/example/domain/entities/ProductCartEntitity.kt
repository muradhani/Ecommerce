package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class ProductCartEntitity(
    @PrimaryKey
    val id: Int,
    val thumbnail: String,
    val title: String,
    val count : Int,
    val price: Int,
)
