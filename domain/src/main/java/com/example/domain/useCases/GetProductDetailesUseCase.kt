package com.example.domain.useCases

import com.example.domain.mapper.ProductMapper
import com.example.domain.models.Product
import com.example.domain.models.states.State
import com.example.domain.repo.ProductRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductDetailesUseCase @Inject constructor(
    private val getProductUseCase : ProductRepoInterface,
    private val mapper: ProductMapper
) {
    suspend operator fun invoke(productID :String): Flow<State<Product>> {
        return flow {
            emit(State.Loading)
            getProductUseCase.getProductData(productID).collect{state ->
                if (state is State.Success){
                    emit(State.Success(mapper.map(state.toData()!!)))
                }
            }
        }
    }

}