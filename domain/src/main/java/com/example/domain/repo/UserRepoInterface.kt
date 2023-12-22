package com.example.domain.repo

import com.example.domain.models.State
import com.example.domain.models.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepoInterface {
     fun createAccountWithEmailAndPassword(email:String,password:String):Flow<State<String>>
}