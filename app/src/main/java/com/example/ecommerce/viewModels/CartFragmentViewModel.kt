package com.example.ecommerce.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import com.example.domain.useCases.GetAllcartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    private val getCartItem : GetAllcartItemsUseCase
) : ViewModel() {
    private val _cartItems = MutableStateFlow<State<List<ProductCartEntitity>>>(State.Loading)
    val cartItems :StateFlow<State<List<ProductCartEntitity>>> = _cartItems
   init {
       fetchCartItems()
   }
    fun fetchCartItems(){
        viewModelScope.launch {
            getCartItem().collect{
                _cartItems.value = it
            }
        }
    }
}