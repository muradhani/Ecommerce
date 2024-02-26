package com.example.domain.useCases

import com.example.domain.entities.CartItems
import com.example.domain.models.states.State
import com.example.domain.repo.CartRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllcartItemsUseCase @Inject constructor(
    private val cartRepo: CartRepoInterface
){
    suspend operator fun invoke() :Flow<State<CartItems>> {
        return flow {
            cartRepo.getAllCartItem().collect{
                if (it is State.Success){
                    val items = it.data
                    var totalPrice =0
                    for(item in items){
                        totalPrice += item.price*item.count
                    }
                    emit(State.Success(CartItems(items.toMutableList(),totalPrice)))
                }
            }
        }
    }
}