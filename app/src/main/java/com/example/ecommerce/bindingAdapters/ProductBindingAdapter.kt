package com.example.ecommerce.bindingAdapters

import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
fun <T,DB:ViewDataBinding> submitList(view: RecyclerView,list:List<T>?){
    val adapter = view.adapter as BaseRecyclerViewAdapterAdapter<DB,T>
    adapter.setData(list?: emptyList())
}