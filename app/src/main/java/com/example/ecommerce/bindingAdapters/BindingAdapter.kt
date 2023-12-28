package com.example.ecommerce.bindingAdapters

import android.view.View
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.domain.models.states.CheckEmailExistenceState
import com.example.domain.models.states.State
import com.example.domain.models.states.ValidateEmailState
import com.example.domain.models.states.ValidatePasswordState
import com.example.ecommerce.R
import com.google.android.material.snackbar.Snackbar

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
@BindingAdapter(value = ["emailExistState"])
fun setEmailExistState(textView: TextView, state: CheckEmailExistenceState?) {
    when (state) {
        is CheckEmailExistenceState.EmailExist -> {
            textView.error = "This email already exists."

        }
        is CheckEmailExistenceState.EmailNotExist -> {
        }
        // Handle other states if needed
        else -> {}
    }
}

@BindingAdapter(value=["emailValidate"])
fun EmailValidate(textView: TextView, state: ValidateEmailState?) {
    when (state) {
        is ValidateEmailState.EmailExist -> {
            textView.error = "This email already exists."

        }
        is ValidateEmailState.Error -> {
            textView.error =state.message

        }
        is ValidateEmailState.validEmail -> {
        }
        // Handle other states if needed
        else -> {}
    }
}

@BindingAdapter(value=["passwordValidate"])
fun passwordValidate(textView: TextView, state: ValidatePasswordState?) {
    when (state) {
        is ValidatePasswordState.Error -> {
            textView.error = state.message

        }
        is ValidatePasswordState.ValidPassword -> {
        }
        // Handle other states if needed
        else -> {}
    }
}

@BindingAdapter(value = ["showSnackbar"])
fun <T> showSnackbar(
    view: View,
    state: State<T>?
) {
    var message :String
    if (state is State.Success){
        message = "User registered successfully"

    }
    else if (state is State.Error){
       message = state.message
    }
    val snackbar = Snackbar.make(view, "User registered successfully", Snackbar.LENGTH_SHORT)
    snackbar.show()

}