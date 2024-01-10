package com.example.domain.useCases

import android.util.Log
import com.example.domain.entities.Product
import com.example.domain.models.states.State
import com.example.domain.repo.CategoriesRepoInterface
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCategoryProductsUseCase @Inject constructor(
    private val categoriesRepoInterface: CategoriesRepoInterface
) {
    suspend operator fun invoke(category: String): Flow<State<List<List<Product>>>> {
        return flow {
            try {

                categoriesRepoInterface.getCategoryProducts(category).collect { products ->
                    emit(State.Loading)
                    val list = products.toData()
                    Log.i("usecase", list.toString())
                    val bestDeals = list!!.sortedBy { it.price }.take(2)
                    val bestProducts = list.sortedByDescending { it.rating }.take(3)
                    Log.i("usecasebestProducts", bestProducts.toString())
                    emit(State.Success(listOf(list, bestDeals, bestProducts)))
                }
            } catch (e: Exception) {
                Log.i("usecase", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}