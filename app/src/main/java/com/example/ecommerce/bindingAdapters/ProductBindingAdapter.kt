package com.example.ecommerce.bindingAdapters

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.Product
import com.example.ecommerce.base.BaseRecyclerViewAdapterAdapter
import com.example.ecommerce.base.BaseViewHolder


@BindingAdapter("setImage")
fun loadImage(view: View, imgurl:String){
    Glide.with(view.context).load(imgurl).into(view as ImageView)
}

@BindingAdapter("setAdapter")
fun <DB : ViewDataBinding> setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<BaseViewHolder<DB>>?) {
    adapter?.let {
        view.adapter = it
    }
}
@BindingAdapter("submitList")
fun < T, DB : ViewDataBinding>  submitList(view: RecyclerView, list:List<T>?){
    val adapter = view.adapter as BaseRecyclerViewAdapterAdapter<DB,T>
    adapter.setData(list?: emptyList<Product>())

}

@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView, strikeThrough: Boolean) {
    if (strikeThrough) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}