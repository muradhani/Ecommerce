package com.example.domain.useCases

import android.util.Patterns
import com.example.domain.models.states.CheckEmailExistenceState
import com.example.domain.models.states.State
import com.example.domain.models.states.ValidateEmailState
import com.example.domain.repo.UserRepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateEmailRegisterUseCase @Inject constructor(
    private val repo: UserRepoInterface,
){
    lateinit var state : State<ValidateEmailState>
    suspend operator fun invoke(email:String):Flow<ValidateEmailState>{
        return flow {
            val emailState = validateEmail(email)
            if (emailState is ValidateEmailState.validEmail){
                emit(ValidateEmailState.validEmail)
            }
            else{
                emit(ValidateEmailState.InvalidEmail)
            }
        }
    }
    private suspend fun validateEmail(email: String):ValidateEmailState{
        lateinit var state :ValidateEmailState
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            state = ValidateEmailState.validEmail
        }else{
            state = ValidateEmailState.InvalidEmail
        }
        return state
    }
}