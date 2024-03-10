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

    override suspend fun getAllCartItem(): Flow<State<List<ProductCartEntitity>>> {
        return flow {
            emit(State.Loading)
            emit(State.Success(cartDao.getAllUserCartProducts()))
        }
    }

    override suspend fun DeleteItem(product: ProductCartEntitity): Flow<State<Int>> {
       return flow {
           var result = cartDao.deleteItem(product)
           if (result == 1) {
               emit(State.Success(result))
           }
           else{
               emit(State.Error("item not deleted"))
           }
       }
    }

    override suspend fun replaceCountNumber(productId: Int, newCount: Int): Flow<State<Int>> {
        return flow {
            var result = cartDao.replaceProductCount(productId,newCount)
            if (result == 1) {
                emit(State.Success(result))
            }
            else{
                emit(State.Error("item not deleted"))
            }
        }
    }
}