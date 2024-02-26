package com.example.domain.useCases

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import com.example.domain.repo.CartRepoInterface
import com.example.domain.repo.CategoriesRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteItemCartUseCase @Inject constructor(
    private val cartrepo: CartRepoInterface
) {
    suspend operator fun invoke(product:ProductCartEntitity): Flow<State<Int>> {
        return flow {
            cartrepo.DeleteItem(product).collect{
                if (it is State.Success){
                    emit(State.Success(it.data))
                }
                else{
                    emit(State.Error("item not deleted"))
                }
            }
        }
    }
}