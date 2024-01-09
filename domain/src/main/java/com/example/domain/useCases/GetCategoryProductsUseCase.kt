package com.example.domain.useCases

import com.example.domain.entities.Product
import com.example.domain.models.states.State
import com.example.domain.repo.CategoriesRepoInterface
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class GetCategoryProductsUseCase @Inject constructor(
    private val categoriesRepoInterface: CategoriesRepoInterface
){
    suspend operator fun invoke(category:String):Flow<State<List<List<Product>>>> {
        return flow{
            emit(State.Loading)
            categoriesRepoInterface.getCategoryProducts(category).collect{ products ->
                val list = products.toData()
                val bestDeals = list!!.sortedBy { it.price }.take(3)
                val bestProducts = list.sortedByDescending { it.rating.toInt() }.take(3)
                emit(State.Success(listOf(list,bestDeals,bestProducts)))
            }
        }
    }
}