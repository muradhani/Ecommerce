package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Product
import com.example.domain.models.states.State
import com.example.domain.useCases.GetAllCategoriesUseCase
import com.example.domain.useCases.GetCategoryProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase
) : ViewModel() {

    private val _bestDealsLiveData = MutableLiveData<List<Product>>()
    val bestDealsLiveData: LiveData<List<Product>> get() = _bestDealsLiveData

    private val _bestProductsLiveData = MutableLiveData<List<Product>>()
    val bestProductsLiveData: LiveData<List<Product>> get() = _bestProductsLiveData

    private val _allProductsLiveData = MutableLiveData<List<Product>>()
    val allProductsLiveData: LiveData<List<Product>> get() = _allProductsLiveData

    private val _filteredProducts = MutableLiveData<State<List<List<Product>>>>()
    val filteredProducts: LiveData<State<List<List<Product>>>> get() = _filteredProducts

    fun getProducts(category:String){
        viewModelScope.launch {
            getCategoryProductsUseCase(category).collect{state ->
                when (state) {
                    is State.Success -> {
                        val (bestDeals, bestProducts, allProductsList) = (state.toData() as? Triple<List<Product>, List<Product>, List<Product>>)
                            ?: Triple(emptyList(), emptyList(), emptyList())
                        _bestDealsLiveData.postValue(bestDeals)
                        _bestProductsLiveData.postValue(bestProducts)
                        _allProductsLiveData.postValue(allProductsList)
                    }

                    is State.Loading -> {
                        _filteredProducts.postValue(state)
                    }
                    else -> {}
                }
            }
        }
    }
}