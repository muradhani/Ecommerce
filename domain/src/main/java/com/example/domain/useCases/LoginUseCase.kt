package com.example.domain.useCases

import com.example.domain.models.states.State
import com.example.domain.models.states.UserLoginState
import com.example.domain.repo.SharedPreferenceRepoInterface
import com.example.domain.repo.UserRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val validateEmailRegisterUseCase: ValidateEmailRegisterUseCase,
    private val repo: UserRepoInterface,
    private val sharedPreferenceRepository: SharedPreferenceRepoInterface
){
    suspend operator fun invoke(email : String, password : String): Flow<UserLoginState> {
        return flow {
            emit(UserLoginState.Loading)
                    repo.loginWithEmailAndPassword(email,password).collect{
                        if (it is State.Success){
                            sharedPreferenceRepository.saveLoginState(true)
                            emit(UserLoginState.LoginSuccess(it.data))
                        }else if (it is State.Error){
                            emit(UserLoginState.Error(it.message))
                        }
                    }
                }

        }
    }
