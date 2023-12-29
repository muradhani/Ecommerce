package com.example.domain.models.states

sealed class UserLoginState{
    object Loading : UserLoginState()
    data class LoginSuccess(val uid:String) : UserLoginState()
    data class Error(val message: String) : UserLoginState()
}
