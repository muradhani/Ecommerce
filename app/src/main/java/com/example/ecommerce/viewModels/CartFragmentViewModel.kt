package com.example.ecommerce.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.CartItems
import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import com.example.domain.useCases.DeleteItemCartUseCase
import com.example.domain.useCases.GetAllcartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    private val getCartItem : GetAllcartItemsUseCase,
    private val deleteCartItem: DeleteItemCartUseCase
) : ViewModel() {
    private val _cartItems = MutableStateFlow<State<CartItems>>(State.Loading)
    val cartItems :StateFlow<State<CartItems>> = _cartItems
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
    fun deleteItem(position:Int){
        viewModelScope.launch {
            deleteCartItem(_cartItems.value.toData()!!.items.get(position)).collect{
                if (it is State.Success){
                    fetchCartItems()
                }
            }
        }
    }
}