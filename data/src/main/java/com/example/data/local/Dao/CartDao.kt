package com.example.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.entities.ProductCartEntitity

@Dao
interface CartDao {
    @Insert
    suspend fun addProductToCart(item:ProductCartEntitity):Long
    @Query("SELECT * FROM cart")
    suspend fun getAllUserCartProducts():List<ProductCartEntitity>
}