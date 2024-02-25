package com.example.domain.repo

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import kotlinx.coroutines.flow.Flow

interface CartRepoInterface {
    suspend fun addToCart(item : ProductCartEntitity): Flow<State<Boolean>>
    suspend fun getAllCartItem():Flow<State<List<ProductCartEntitity>>>
}