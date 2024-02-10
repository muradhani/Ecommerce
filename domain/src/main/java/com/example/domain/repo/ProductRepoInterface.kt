package com.example.domain.repo

import com.example.domain.entities.ProductEntity
import com.example.domain.models.Product
import com.example.domain.models.states.State
import kotlinx.coroutines.flow.Flow

interface ProductRepoInterface {
    suspend fun getProductData(productId:String): Flow<State<ProductEntity>>

}