package com.example.domain.useCases

import android.util.Patterns
import com.example.domain.models.states.ValidateEmailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateEmailRegisterUseCase @Inject constructor(
){
    suspend operator fun invoke(email: String): Flow<ValidateEmailState> = flow {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()) {
            emit(ValidateEmailState.validEmail)
        } else if (email.isEmpty()) {
            emit(ValidateEmailState.Error("Email cannot be empty"))
        } else {
            emit(ValidateEmailState.Error("Email is bad formatted"))
        }
    }
}
