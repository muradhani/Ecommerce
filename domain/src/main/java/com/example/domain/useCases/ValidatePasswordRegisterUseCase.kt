package com.example.domain.useCases

import com.example.domain.models.states.ValidateEmailState
import com.example.domain.models.states.ValidatePasswordState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidatePasswordRegisterUseCase @Inject constructor() {
    suspend operator fun invoke(password:String): Flow<ValidatePasswordState> {
        return flow{
            if (password.length < 8){
                emit(ValidatePasswordState.Error("the password cannot be shorter than 8 characters"))
            }
            else{
                emit(ValidatePasswordState.ValidPassword)
            }
        }
    }
}