package com.example.ecommerce.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.domain.models.State

@BindingAdapter(value = ["showingLoading"])
fun <T> showingLoading(view : View, state:State<T>){
    when (state) {
        is State.Loading -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}