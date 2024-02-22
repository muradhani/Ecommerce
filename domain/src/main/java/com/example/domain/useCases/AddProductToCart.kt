package com.example.domain.useCases

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.mapper.ProductToProductCart
import com.example.domain.models.Product
import com.example.domain.models.states.State
import com.example.domain.repo.CartRepoInterface
import com.example.domain.repo.ProductRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductToCart @Inject constructor(
    private val cartRepo : CartRepoInterface,
    private val mapper: ProductToProductCart
) {
    suspend operator fun invoke(item : Product): Flow<State<Boolean>> {
        return flow {
            val item = mapper.map(item)
            cartRepo.addToCart(item).collect{
                if (it is State.Success){
                    emit(State.Success(true))
                }
            }
        }
    }
}