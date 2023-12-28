package com.example.data.repo

import com.example.domain.models.states.State
import com.example.domain.repo.UserRepoInterface
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.SignInMethodQueryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class UserRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): UserRepoInterface {
    override  fun createAccountWithEmailAndPassword(email: String, password: String) : Flow<State<String>> {
        return  flow {
            try {
                emit(State.Loading)
                val authResult = suspendCoroutine<AuthResult> { continuation ->
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener { authResult ->
                            continuation.resume(authResult)
                        }
                        .addOnFailureListener { exception ->
                            continuation.resumeWithException(exception)
                        }
                }
                val uid = authResult.user!!.uid
                emit(State.Success(uid))
            } catch (e: Exception) {
                // User creation failed, handle the exception
                emit(State.Error(e.message ?: "Unknown error"))
            }
        }
    }

    override fun checkIfEmailAlreadyExist(email: String): Flow<State<String>> {
       return flow {
           try {
               val result = Tasks.await(firebaseAuth.fetchSignInMethodsForEmail(email))

               if (result.signInMethods != null && result.signInMethods!!.isNotEmpty()) {
                   // Email exists, handle accordingly
                   emit(State.Success("Email already exists"))
               } else {
                   // Email does not exist
                   emit(State.Success("Email does not exist"))
               }
           } catch (e: FirebaseAuthInvalidUserException) {
               // This exception is thrown if the email is not registered
               emit(State.Success("Email does not exist"))
           } catch (e: Exception) {
               // Handle other exceptions
               emit(State.Error("Error checking email existence: ${e.message}"))
           }

       }
    }
}