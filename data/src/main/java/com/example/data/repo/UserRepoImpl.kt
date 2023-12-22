package com.example.data.repo

import com.example.domain.models.State
import com.example.domain.models.User
import com.example.domain.repo.UserRepoInterface
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class UserRepoImpl (
    private val firebaseAuth: FirebaseAuth
): UserRepoInterface {
    override  fun createAccountWithEmailAndPassword(email: String, password: String) : Flow<State<String>> {
        return  flow {
            try {
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
}