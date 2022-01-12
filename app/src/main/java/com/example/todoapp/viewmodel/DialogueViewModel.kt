package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.Action
import com.example.core.DeleteUseCase
import com.example.core.DismissDialogue
import com.example.core.Pages
import com.example.todoapp.Events
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class DialogueViewModel @Inject constructor(
    val dismissDialogue: DismissDialogue,
    val deleteUseCase: DeleteUseCase
):ObservableViewModel(),LifecycleObserver {

    private val _dismiss=MutableLiveData<Events<Pages>>()
    val dismiss:LiveData<Events<Pages>> =_dismiss

    fun onSuccesspressed(id:Int){
        deleteUseCase(id).onEach {
            when(it.action){
                Action.SUCCESS->{

                }
            }

        }.launchIn(viewModelScope)
    }


    fun onCancelpressed(){
        dismissDialogue.invoke().onEach {
            when(it.action){
                Action.DISMISS->{
                    _dismiss.value=Events(it.target!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}