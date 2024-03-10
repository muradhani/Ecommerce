package com.example.domain.useCases

import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import com.example.domain.repo.CartRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class SetCartProductCountNumberUseCase @Inject constructor(
    private val dao : CartRepoInterface
) {
    suspend operator fun invoke(productId: Int, newCount: Int): Flow<State<Int>>{
        return flow{
            emit(State.Loading)
            dao.replaceCountNumber(productId, newCount).collect{
                emit(it)
            }
        }
    }
}