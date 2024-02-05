package com.example.ecommerce.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.states.State
import com.example.domain.useCases.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Triple


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {
    val categoriesList = MutableLiveData<List<String>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllCategoriesUseCase().collect {state ->
                when (state) {
                    is State.Success -> {
                        withContext(Dispatchers.Main){
                            categoriesList.postValue(state.toData())
                        }
                    }
                    is State.Loading -> {
                    }
                    else -> {}
                }
            }
        }
    }

}