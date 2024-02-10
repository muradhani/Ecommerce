package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entities.ProductEntity
import com.example.domain.models.Product
import com.example.domain.models.states.State
import com.example.domain.repo.ProductRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.spec.ECField
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val apiService: ApiService
) :ProductRepoInterface{
    override suspend fun getProductData(productId: String): Flow<State<ProductEntity>> {
        return flow {
            emit(State.Loading)
            try {
                val result = apiService.getProductData(productId)
                if (result.isSuccessful){
                    emit(State.Success(result.body()!!))
                }
            }catch (e:Exception){
                emit(State.Error(e.message!!))
            }
        }
    }
}