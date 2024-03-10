package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class ProductCartEntitity(
    @PrimaryKey
    val id: Int,
    val thumbnail: String,
    val title: String,
    var count : Int,
    var price: Int,
)
//{
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other !is ProductCartEntitity) return false
//
//        return id == (other as ProductCartEntitity).id
//    }
//
//    override fun hashCode(): Int {
//        return id
//    }
//}
