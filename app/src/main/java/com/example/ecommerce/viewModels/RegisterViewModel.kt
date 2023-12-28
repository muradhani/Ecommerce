package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.states.State
import com.example.domain.models.User
import com.example.domain.models.states.CheckEmailExistenceState
import com.example.domain.models.states.ValidateEmailState
import com.example.domain.models.states.ValidatePasswordState
import com.example.domain.useCases.CheckIfEmailExistUseCase
import com.example.domain.useCases.RegisterUserUseCase
import com.example.domain.useCases.ValidateEmailRegisterUseCase
import com.example.domain.useCases.ValidatePasswordRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val checkEmailExsitanceUseCase: CheckIfEmailExistUseCase,
    private val validateEmail: ValidateEmailRegisterUseCase,
    private val validatePassword: ValidatePasswordRegisterUseCase
) : ViewModel() {
    val user: MutableLiveData<User> = MutableLiveData<User>(User())
    private val _register = MutableLiveData<State<String>>()
    val register: LiveData<State<String>> = _register
    private var emailJob: Job? = null
    private val _emailExist = MutableLiveData<CheckEmailExistenceState>()
    val emailExist: LiveData<CheckEmailExistenceState> = _emailExist

    private val _emailValidation = MutableLiveData<ValidateEmailState>()
    val emailValidation: LiveData<ValidateEmailState> = _emailValidation

    private val _passwordValidation = MutableLiveData<ValidatePasswordState>()
    val passwordValidation: LiveData<ValidatePasswordState> = _passwordValidation
    fun registerUser() {
        viewModelScope.launch {
            user.value?.takeIf { it.email.isNotEmpty() && it.password.isNotEmpty() }
                ?.let { user ->
                    validateEmail(user.email).collect { validateEmailState ->
                        if (validateEmailState is ValidateEmailState.validEmail) {
                            validatePassword(user.password).collect { it ->
                                if (it is ValidatePasswordState.ValidPassword) {
                                    registerUserUseCase(user.email.trim(), user.password.trim())
                                        .collect { state ->
                                            _register.postValue(state)
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

    fun onTextChanged(s: CharSequence?) {
        emailJob?.cancel()
        emailJob = viewModelScope.launch {
            delay(2000)
            checkEmailExsitanceUseCase(s.toString()).collect {
                _emailExist.postValue(it)
            }
        }
    }
}