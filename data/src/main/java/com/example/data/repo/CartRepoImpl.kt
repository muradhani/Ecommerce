package com.example.data.repo

import com.example.data.local.Dao.CartDao
import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import com.example.domain.repo.CartRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val cartDao: CartDao
):CartRepoInterface {
    override suspend fun addToCart(item: ProductCartEntitity):Flow<State<Boolean>> {
        return flow {
            try {
                emit(State.Loading)
                val id = cartDao.addProductToCart(item)
                if (id > 1) emit(State.Success(true)) else emit(State.Error("item not inserted"))
            }catch (e:Exception){
                emit(State.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getAllCartItem(): List<ProductCartEntitity> {
        TODO("Not yet implemented")
    }
}