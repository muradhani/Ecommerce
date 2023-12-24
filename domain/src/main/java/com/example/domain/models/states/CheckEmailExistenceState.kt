package com.example.domain.models.states

sealed class CheckEmailExistenceState : State<Boolean>() {
    object EmailExist : CheckEmailExistenceState()
    object EmailNotExist : CheckEmailExistenceState()
    data class Error(val message: String) : CheckEmailExistenceState()

    object Loading : CheckEmailExistenceState()
}
