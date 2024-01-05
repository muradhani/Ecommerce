package com.example.domain.repo

import com.example.domain.models.states.State
import kotlinx.coroutines.flow.Flow


interface CategoriesRepoInterface {
    suspend fun getAllCategories(): Flow<State<List<String>>>
}