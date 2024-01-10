package com.example.ecommerce.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapterAdapter< DB : ViewDataBinding,T>(initialData: List<T>):
    RecyclerView.Adapter<BaseViewHolder<DB>>() {
    private var data: List<T> = initialData
    var itemClickListener: OnItemClickListener<T>? = null
    abstract fun bind(binding: DB,item: T)
    fun setData(newData: List<*>) {
        val diffResult = DiffUtil.calculateDiff(BaseDiffCallback(data, newData))
        data = newData as List<T>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val binding = DataBindingUtil.inflate<DB>(
            LayoutInflater.from(parent.context),
            getLayoutId(),
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        val item = data[position]
        bind(holder.binding,item)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    abstract fun getLayoutId(): Int



    private class BaseDiffCallback<T>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}
interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}
class BaseViewHolder<DB : ViewDataBinding>(val binding: DB) :
    RecyclerView.ViewHolder(binding.root) {
}