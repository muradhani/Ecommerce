package com.example.domain.useCases

import com.example.domain.repo.CartRepoInterface
import javax.inject.Inject

class GetAllcartItemsUseCase @Inject constructor(
    private val cartRepo: CartRepoInterface
){
    suspend operator fun invoke() =  cartRepo.getAllCartItem()
}