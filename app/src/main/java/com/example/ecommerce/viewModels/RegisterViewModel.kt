package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.State
import com.example.domain.models.User
import com.example.domain.useCases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase :RegisterUserUseCase,
): ViewModel() {
   val user: MutableLiveData<User> = MutableLiveData<User>(User())
    private val _register = MutableLiveData<State<String>>()
    val register : LiveData<State<String>> = _register
    public suspend fun registerUser(){
        user.value?.takeIf { it.email.isNotEmpty() && it.password.isNotEmpty() }
            ?.let { (email, password) ->
                registerUserUseCase(email.trim(), password.trim())
                    .collect { state ->
                        _register.postValue(state)
                    }
            }
    }
}