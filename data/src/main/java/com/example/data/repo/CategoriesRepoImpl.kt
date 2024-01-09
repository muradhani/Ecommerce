package com.example.data.repo

import android.util.Log
import com.example.data.mapper.ProductMapper
import com.example.data.remote.ApiService
import com.example.domain.entities.Product
import com.example.domain.models.states.State
import com.example.domain.repo.CategoriesRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val productMapper: ProductMapper
) : CategoriesRepoInterface {
    override suspend fun getAllCategories(): Flow<State<List<String>>> {
        return flow{
            try {
                emit(State.Loading)
                val result = apiService.getCategories()
                if (result.isSuccessful){
                    emit(State.Success(result.body()!!.toList()))
                }else{
                    emit(State.Error(result.message()))
                }
            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getCategoryProducts(category: String): Flow<State<List<Product>>> {
        return flow {
            try {
                emit(State.Loading)
                val result = apiService.getProductsInCategory(category)
                if (result.isSuccessful){
                    Log.i("main",result.body().toString())
                    emit(State.Success(result.body()!!.map {
                        productMapper.map(it)
                    }))
                }else{
                    emit(State.Error(result.message()))
                }
            } catch (e: Exception) {
                Log.i("main",e.message.toString())
            }
        }
    }
}