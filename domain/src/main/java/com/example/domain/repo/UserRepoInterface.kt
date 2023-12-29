package com.example.domain.repo

import com.example.domain.models.states.State
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface UserRepoInterface {
     suspend fun createAccountWithEmailAndPassword(email:String,password:String):Flow<State<String>>
     suspend fun checkIfEmailAlreadyExist(email: String):Flow<State<String>>

     suspend fun loginWithEmailAndPassword(email: String,password: String):Flow<State<String>>
}