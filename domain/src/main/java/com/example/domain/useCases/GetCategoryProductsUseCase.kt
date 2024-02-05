package com.example.domain.useCases

import com.example.domain.mapper.ProductMapper
import com.example.domain.models.Product
import com.example.domain.models.states.State
import com.example.domain.repo.CategoriesRepoInterface

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class GetCategoryProductsUseCase @Inject constructor(
    private val categoriesRepoInterface: CategoriesRepoInterface,
    private val mapper: ProductMapper
) {
    suspend operator fun invoke(category: String): Flow<State<List<List<Product>>>> {
        return flow {
            try {
                categoriesRepoInterface.getCategoryProducts(category).collect {
                    emit(State.Loading)
                    val list = it.toData()!!.map { mapper.map(it) }
                    val bestDeals = list.sortedBy { it.newprice }.take(2)
                    val bestProducts = list.sortedByDescending { it.rating }.take(3)
                    emit(State.Success(listOf(list, bestDeals, bestProducts)))
                }
            } catch (e: Exception) {
            }
        }.flowOn(Dispatchers.IO)
    }
}