package com.example.todoapp

import android.util.Log
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.viewmodel.ItemViewModel


class Differer(oldList: List<ItemViewModel>?, newList: List<ItemViewModel>?) :
    DiffUtil.Callback() {
    private val mOldList: List<ItemViewModel>?
    private val mNewList: List<ItemViewModel>?
    override fun getOldListSize(): Int {
        return mOldList?.size?:0
    }

    override fun getNewListSize(): Int {
        return mNewList?.size?:0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result = if(mOldList!=null&&mNewList!=null){
            val oldItem = mOldList[oldItemPosition]
            val newItem = mNewList[newItemPosition]
            val same = (oldItem.id == newItem.id)
            same
        }
        else{
            false
        }
        Log.d("differ_bug","$result")
        return result
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if(mOldList!=null&&mNewList!=null){
            val oldItem: ItemViewModel = mOldList[oldItemPosition]
            val newItem: ItemViewModel = mNewList[newItemPosition]
            return oldItem.isEqual(newItem)
        }
        else{
            return false
        }
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    init {
        mOldList = oldList
        mNewList = newList
    }
}