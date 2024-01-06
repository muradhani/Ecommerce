package com.example.domain.repo

interface SharedPreferenceRepoInterface {
    fun saveLoginState(isLoggedIn: Boolean)
    fun getLoginState(): Boolean
}