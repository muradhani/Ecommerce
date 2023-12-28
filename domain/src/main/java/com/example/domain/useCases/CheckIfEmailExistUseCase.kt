package com.example.domain.useCases

import com.example.domain.models.states.CheckEmailExistenceState
import com.example.domain.models.states.State
import com.example.domain.models.states.ValidateEmailState
import com.example.domain.repo.UserRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckIfEmailExistUseCase @Inject constructor(
    private val repo: UserRepoInterface,
    private val checkEmailExistance: ValidateEmailRegisterUseCase

) {
    suspend operator fun invoke(email: String): Flow<CheckEmailExistenceState> {
        return flow {
            emit(CheckEmailExistenceState.Loading)
            checkEmailExistance(email).collect { validateEmailState ->
                if (validateEmailState is ValidateEmailState.validEmail) {
                    repo.checkIfEmailAlreadyExist(email).collect { state ->
                        when (state) {
                            is State.Success -> {
                                val data = state.toData()
                                if (data != null && data.isNotEmpty()) {
                                    emit(CheckEmailExistenceState.EmailExist)
                                } else {
                                    emit(CheckEmailExistenceState.EmailNotExist)
                                }
                            }

                            is State.Error -> {
                                emit(CheckEmailExistenceState.Error(state.message))
                            }

                            else -> {}
                        }
                    }
                }
            }

        }
    }
}

