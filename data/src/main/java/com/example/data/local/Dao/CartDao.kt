package com.example.data.local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.entities.ProductCartEntitity

@Dao
interface CartDao {
    @Insert
    suspend fun addProductToCart(item:ProductCartEntitity):Long
    @Query("SELECT * FROM cart")
    suspend fun getAllUserCartProducts():List<ProductCartEntitity>

    @Delete
    suspend fun deleteItem(item:ProductCartEntitity):Int


    @Query("UPDATE cart SET count = :newCount WHERE id = :productId")
    suspend fun replaceProductCount(productId: Int, newCount: Int): Int
}