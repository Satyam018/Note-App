package com.example.todoapp.viewmodel

import android.widget.CompoundButton
import androidx.lifecycle.LifecycleObserver
import com.example.todoapp.ItemViewType
import com.example.todoapp.R

class NoteItemViewModel(
    val initialIndex:Int,
    val data:com.example.core.module.Notes,
    var onDeleteListener:((NoteItemViewModel)->Unit)?,
    var onClickListener: ((NoteItemViewModel)->Unit)?,
    var onQuantityChangeListener: ((NoteItemViewModel, Int)->Unit)?,
    var onStatusChangedListener:((NoteItemViewModel,Boolean)->Unit)?
):ItemViewModel,ObservableViewModel(),LifecycleObserver {
    override val layoutid:Int= R.layout.note_item_layout
    override val viewType: ItemViewType=ItemViewType.NOTE_ITEM
    override val id:Int=data.id
    override var index:Int=initialIndex

    override fun isEqual(other: ItemViewModel): Boolean {
        return if (other is NoteItemViewModel){
            data.isEqual(other.data)
        }else{
            false
        }
    }

    fun onDeletePressed(){
        onDeleteListener?.invoke(this)
    }

    override fun onCleared() {
        onClickListener=null
        onQuantityChangeListener=null

        super.onCleared()
    }
    fun onLikeClicked(){
        //count.value=(count.value?:0)+1

        //onQuantityChangeListener?.invoke(this,count.value?:0)
    }
    fun onItemClicked() {
        onClickListener?.invoke(this)
    }

    fun onCheckBoxClicked(button:CompoundButton,checked:Boolean){
        onStatusChangedListener?.invoke(this,checked)


    }



}