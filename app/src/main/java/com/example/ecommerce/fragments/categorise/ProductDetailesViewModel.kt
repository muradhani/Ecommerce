package com.example.ecommerce.fragments.categorise

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.Product
import com.example.domain.models.states.State
import com.example.domain.useCases.AddProductToCart
import com.example.domain.useCases.GetProductDetailesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailesViewModel @Inject constructor(
    private val getProduct: GetProductDetailesUseCase,
    private val addProductToCart: AddProductToCart
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
    fun addToCart(){
        viewModelScope.launch {
            addProductToCart(product.value.toData()!!).collect{
                if (it is State.Success){
                    Log.i("main","added successfully")
                }
            }
        }
    }
}