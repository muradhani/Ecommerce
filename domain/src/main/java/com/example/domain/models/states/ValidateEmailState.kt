package com.example.domain.models.states

sealed class ValidateEmailState {d
    object validEmail : ValidateEmailState()
    object EmailExist : ValidateEmailState()
    object InvalidEmail : ValidateEmailState()
    data class Error(val message: String) : ValidateEmailState()
}
