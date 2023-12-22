package com.example.domain.useCases


import com.example.domain.repo.UserRepoInterface
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repo : UserRepoInterface
){
    suspend operator fun invoke(email:String,password:String) =
        repo.createAccountWithEmailAndPassword(email,password)

}