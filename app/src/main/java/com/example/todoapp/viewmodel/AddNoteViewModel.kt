package com.example.todoapp.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.Action
import com.example.core.Pages
import com.example.core.SaveNotesUseCase
import com.example.core.TextChangeUseCase
import com.example.todoapp.Events
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    val saveNotesUseCase: SaveNotesUseCase,
    val textChangeUseCase: TextChangeUseCase
):ObservableViewModel(),LifecycleObserver {
    val heading=MutableLiveData("")
    val body=MutableLiveData("")
    val placeText=MutableLiveData("")
    var bundles:Bundle?=null

    private val _movePage=MutableLiveData<Events<Pages>>()
    val movePage:LiveData<Events<Pages>> =_movePage




    fun onSaveClicked(){
        saveNotesUseCase(heading.value!!,body.value!!,bundles!!).onEach {
            if (it.action==Action.Go_To_NewPage){
                _movePage.value=Events(it.target!!)
            }

        }.launchIn(viewModelScope)


    }
    fun getBundle(bundle: Bundle){
        bundles=bundle
        heading.value=bundle.getString("title")
        body.value=bundle.getString("body")

        textChangeUseCase(bundle).onEach {
            when(it.action){
                Action.TEXT->{
                    placeText.value=it.target
                }
            }

        }.launchIn(viewModelScope)

    }


}