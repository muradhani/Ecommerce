package com.example.domain.repo

import com.example.domain.models.states.State
import kotlinx.coroutines.flow.Flow

interface UserRepoInterface {
     fun createAccountWithEmailAndPassword(email:String,password:String):Flow<State<String>>
     fun checkIfEmailAlreadyExist(email: String):Flow<State<String>>
}