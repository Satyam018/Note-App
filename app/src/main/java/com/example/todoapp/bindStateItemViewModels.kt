package com.example.todoapp

import android.view.ViewTreeObserver
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone1.BindableRecyclerViewAdapter
import com.example.todoapp.viewmodel.ItemViewModel


@BindingAdapter("itemViewModels")
fun bindStateItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)

}

private fun getOrCreateAdapter(recyclerView: RecyclerView): BindableRecyclerViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerViewAdapter) {
        recyclerView.adapter as BindableRecyclerViewAdapter
    }
    else
    {
        val bindableRecyclerAdapter = BindableRecyclerViewAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}