package com.example.capstone1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.*

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.BR
import com.example.todoapp.Differer
import com.example.todoapp.ListLayoutType
import com.example.todoapp.model.ListLayoutData
import com.example.todoapp.viewmodel.ItemViewModel


class BindableRecyclerViewAdapter : RecyclerView.Adapter<BindableViewHolder>() {

    var itemViewModels: List<ItemViewModel> = emptyList()
    private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewTypeToLayoutId[viewType] ?: 0,
            parent,
            false)
        return BindableViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = itemViewModels[position]
        if (!viewTypeToLayoutId.containsKey(item.viewType.ordinal)) {
            viewTypeToLayoutId[item.viewType.ordinal] = item.layoutid
        }
        return item.viewType.ordinal
    }

    override fun getItemCount(): Int = itemViewModels.size

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(itemViewModels[position])
    }

    fun updateItems(items: List<ItemViewModel>?) {
        /*itemViewModels = items ?: emptyList()
        notifyDataSetChanged()*/


        val diffCallback = Differer(this.itemViewModels, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemViewModels = items ?: emptyList()
        diffResult.dispatchUpdatesTo(this)
    }
}

class BindableViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemViewModel: ItemViewModel) {
        binding.setVariable(BR.itemviewModel, itemViewModel)
    }
}

@BindingAdapter("app:layoutData")
fun RecyclerView.setLayout(layoutData: ListLayoutData) {
    when(layoutData.type){
        ListLayoutType.LINEAR_HORIZONTAL->{
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        }
        ListLayoutType.LINEAR_VERTICAL->{
            layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        }
        ListLayoutType.GRID_HORIZONTAL->{
            layoutManager = GridLayoutManager(context,layoutData.colCount,RecyclerView.HORIZONTAL,false)
        }
        ListLayoutType.GRID_VERTICAL->{
            layoutManager = GridLayoutManager(context,layoutData.colCount,RecyclerView.VERTICAL,false)
        }
    }
}