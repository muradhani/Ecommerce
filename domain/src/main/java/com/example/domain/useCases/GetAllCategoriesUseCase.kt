package com.example.domain.useCases

import com.example.domain.repo.CategoriesRepoInterface
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoriesRepo: CategoriesRepoInterface
) {
    suspend operator fun invoke() = categoriesRepo.getAllCategories()
}