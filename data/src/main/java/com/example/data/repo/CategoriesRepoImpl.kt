package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.models.states.State
import com.example.domain.repo.CategoriesRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val apiService: ApiService
) : CategoriesRepoInterface {
    override suspend fun getAllCategories(): Flow<State<List<String>>> {
        return flow {
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
}