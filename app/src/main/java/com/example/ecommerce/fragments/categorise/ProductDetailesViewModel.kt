package com.example.ecommerce.fragments.categorise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Product
import com.example.domain.models.states.State
import com.example.domain.useCases.GetProductDetailesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailesViewModel @Inject constructor(
    private val getProduct: GetProductDetailesUseCase
): ViewModel() {
    private val _product = MutableStateFlow<State<Product>>(State.Loading)
    val product:StateFlow<State<Product>> = _product
    fun getProductData(productId : String){
        viewModelScope.launch {
            getProduct(productId).collect{
                _product.value = it
            }
        }
    }
}