package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.states.UserLoginState
import com.example.domain.useCases.GetLoginStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getLoginStateUseCase: GetLoginStateUseCase
) :ViewModel(){
    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login
    suspend fun getLoginState(){
        getLoginStateUseCase().collect{
            _login.postValue(it)
        }

    }
}