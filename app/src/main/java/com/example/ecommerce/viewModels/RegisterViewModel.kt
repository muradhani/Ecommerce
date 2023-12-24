package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.states.State
import com.example.domain.models.User
import com.example.domain.models.states.CheckEmailExistenceState
import com.example.domain.useCases.CheckIfEmailExistUseCase
import com.example.domain.useCases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase :RegisterUserUseCase,
    private val checkEmailExsitanceUseCase :CheckIfEmailExistUseCase
): ViewModel() {
   val user: MutableLiveData<User> = MutableLiveData<User>(User())
    private val _register = MutableLiveData<State<String>>()
    val register : LiveData<State<String>> = _register
    private var emailJob: Job? = null
    private val _emailExist = MutableLiveData<CheckEmailExistenceState>()
    val emailExist: LiveData<CheckEmailExistenceState> = _emailExist
    fun registerUser(){
        viewModelScope.launch {
            user.value?.takeIf { it.email.isNotEmpty() && it.password.isNotEmpty() }
                ?.let {
                    registerUserUseCase(it.email.trim(), it.password.trim())
                        .collect { state ->
                            _register.postValue(state)
                        }
                }
        }
    }

    fun onTextChanged(s: CharSequence?) {
        emailJob?.cancel()
        emailJob = viewModelScope.launch {
            delay(2000)
            checkEmailExsitanceUseCase(s.toString()).collect{
                _emailExist.postValue(it)
            }
        }
    }
}