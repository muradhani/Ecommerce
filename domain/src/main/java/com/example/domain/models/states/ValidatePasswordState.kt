package com.example.domain.models.states

sealed class ValidatePasswordState {
    object ValidPassword : ValidatePasswordState()
    data class Error(val message: String) : ValidatePasswordState()
}
