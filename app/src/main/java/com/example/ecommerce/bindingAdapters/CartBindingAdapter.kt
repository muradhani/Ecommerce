package com.example.ecommerce.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.viewModels.CartFragmentViewModel

@BindingAdapter("swipeToDelete", "viewModel")
fun RecyclerView.setSwipeToDelete(enableSwipeToDelete: Boolean?,viewModel:CartFragmentViewModel) {
    if (enableSwipeToDelete!!) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteItem(viewHolder.layoutPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(this)
    }
}
