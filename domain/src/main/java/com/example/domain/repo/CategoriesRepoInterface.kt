package com.example.domain.repo

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.entities.ProductEntity
import com.example.domain.models.states.State
import kotlinx.coroutines.flow.Flow


interface CategoriesRepoInterface {
    suspend fun getAllCategories(): Flow<State<List<String>>>
    suspend fun getCategoryProducts(category:String):Flow<State<List<ProductEntity>>>


}