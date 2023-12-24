package com.example.ecommerce.bindingAdapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.domain.models.states.CheckEmailExistenceState
import com.example.domain.models.states.State

@BindingAdapter(value = ["showingLoading"])
fun <T> showingLoading(view : View, state: State<T>?){
    when (state) {
        is State.Loading -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}
@BindingAdapter(value = ["hidingLoading"])
fun <T> hidingLoading(view : View, state: State<T>?){
    when (state) {
        is State.Loading -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}
@BindingAdapter("emailExistState")
fun setEmailExistState(textView: TextView, state: CheckEmailExistenceState?) {
    when (state) {
        CheckEmailExistenceState.EmailExist -> {
            textView.error = "This email already exists."

        }
        CheckEmailExistenceState.EmailNotExist -> {
        }
        // Handle other states if needed
        else -> {}
    }
}