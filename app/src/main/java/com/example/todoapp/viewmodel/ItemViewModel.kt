package com.example.todoapp.viewmodel

import androidx.annotation.LayoutRes
import com.example.todoapp.ItemViewType

interface ItemViewModel {
    @get:LayoutRes
    val layoutid:Int
    val viewType:ItemViewType
        get() =ItemViewType.NONE
    val id:Int
        get() = 0
    var index:Int

    fun isEqual(other:ItemViewModel):Boolean
}