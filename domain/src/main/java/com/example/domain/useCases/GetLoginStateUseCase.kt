package com.example.domain.useCases

import com.example.domain.repo.SharedPreferenceRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLoginStateUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepoInterface
) {
    suspend operator fun invoke() : Flow<Boolean> {
        return flow{
            emit(sharedPreferenceRepository.getLoginState())
        }
    }
}