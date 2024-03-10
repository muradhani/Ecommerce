package com.example.ecommerce.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.CartItems
import com.example.domain.entities.ProductCartEntitity
import com.example.domain.models.states.State
import com.example.domain.useCases.DeleteItemCartUseCase
import com.example.domain.useCases.GetAllcartItemsUseCase
import com.example.domain.useCases.SetCartProductCountNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    private val getCartItem : GetAllcartItemsUseCase,
    private val deleteCartItem: DeleteItemCartUseCase,
    private val changeProductCount : SetCartProductCountNumberUseCase
) : ViewModel() {
    private val _cartItems = MutableLiveData<State<CartItems>>(State.Loading)
    val cartItems :LiveData<State<CartItems>> = _cartItems
   init {
       fetchCartItems()
   }
    fun fetchCartItems(){
        viewModelScope.launch {
            getCartItem().collect{
                _cartItems.postValue(it)
            }
        }
    }
    fun deleteItem(position:Int){
        viewModelScope.launch {
            deleteCartItem(_cartItems.value!!.toData()!!.items.get(position)).collect{
                if (it is State.Success){
                    fetchCartItems()
                }
            }
        }
    }
    fun increaseCartItemCount(product:ProductCartEntitity){
        viewModelScope.launch {
            changeProductCount(product.id,++product.count).collect{
                if (it is State.Success){
                    fetchCartItems()
                }
            }

        }
    }
    fun decreaseCartItemCount(product:ProductCartEntitity){
       viewModelScope.launch {
           if (product.count >= 0){
               changeProductCount(product.id,--product.count).collect{
                   if (it is State.Success){
                       fetchCartItems()
                   }
               }

           }
       }
    }

}