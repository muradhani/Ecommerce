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
    suspend operator fun invoke() = cartRepo.getAllCartItem()
}