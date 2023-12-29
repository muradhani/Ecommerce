package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.domain.models.states.UserLoginState
import com.example.domain.models.states.ValidateEmailState
import com.example.domain.models.states.ValidatePasswordState
import com.example.domain.useCases.LoginUseCase
import com.example.domain.useCases.ValidateEmailRegisterUseCase
import com.example.domain.useCases.ValidatePasswordRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmail: ValidateEmailRegisterUseCase,
    private val validatePassword: ValidatePasswordRegisterUseCase
) : ViewModel() {
    val user: MutableLiveData<User> = MutableLiveData<User>(User())

    private val _login = MutableLiveData<UserLoginState>()
    val login: LiveData<UserLoginState> = _login

    private val _emailValidation = MutableLiveData<ValidateEmailState>()
    val emailValidation: LiveData<ValidateEmailState> = _emailValidation

    private val _passwordValidation = MutableLiveData<ValidatePasswordState>()
    val passwordValidation: LiveData<ValidatePasswordState> = _passwordValidation
    fun login(){
        viewModelScope.launch {
            viewModelScope.launch {
                user.value?.let { user ->
                    validateEmail(user.email).collect { validateEmailState ->
                        if (validateEmailState is ValidateEmailState.validEmail) {
                            validatePassword(user.password).collect { it ->
                                if (it is ValidatePasswordState.ValidPassword) {
                                    loginUseCase(user.email.trim(), user.password.trim())
                                        .collect { state ->
                                            _login.postValue(state)
                                        }
                                }else{
                                    _passwordValidation.postValue(it)
                                }
                            }
                        }
                        else{
                            _emailValidation.postValue(validateEmailState)
                        }
                    }

                }
            }
        }
    }
}