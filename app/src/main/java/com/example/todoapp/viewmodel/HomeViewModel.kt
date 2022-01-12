package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.*
import com.example.core.module.ANSWER
import com.example.core.module.AskData
import com.example.todoapp.Events
import com.example.todoapp.ListLayoutType
import com.example.todoapp.model.ListLayoutData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val moveToAddFragmentUseCase: MoveToAddFragmentUseCase,
    val getAllNotesUseCase: GetAllNotesUseCase,
    val deleteUseCase: DeleteUseCase,
    val updateStatusUseCase: UpdateStatusUseCase,
    val itemDeleteUseCase: ItemDeleteUseCase,
    val askAnsweredUseCase: AskAnsweredUseCase

):ObservableViewModel(),LifecycleObserver {
    private val _askNotify =MutableLiveData<Events<AskData?>>()
    val askNotify:LiveData<Events<AskData?>> =_askNotify

    var notesData:List<com.example.core.module.Notes> = emptyList()

    val noteItemLayout= ListLayoutData(ListLayoutType.LINEAR_VERTICAL)

   private val _movetopage=MutableLiveData<Events<Pages>>()
    val movetopage:LiveData<Events<Pages>> =_movetopage

fun onAddPressed(){
    moveToAddFragmentUseCase().onEach {
        when(it.action){
            Action.Go_To_NewPage->{
                _movetopage.value=Events(it.target!!)
            }
        }

    }.launchIn(viewModelScope)

}

val noteItems
    get() =_noteItems
    private val _noteItems=MutableLiveData<List<ItemViewModel>>(emptyList())
        init {

            populateItems()


        }



    private fun addRestaurantItem(viewData:MutableList<NoteItemViewModel>, noteItem:List<*>){
        noteItem.forEachIndexed{index,it->
        if (it is com.example.core.module.Notes){
            viewData.add(NoteItemViewModel(index,it,::onDeleteClick,::onNoteItemClick,::onQuantityChanged,::onStatusChanged))
        }

        }
    }
    private fun onQuantityChanged(noteItemViewModel: NoteItemViewModel, i: Int) {

    }

    private fun onStatusChanged(noteItemViewModel: NoteItemViewModel, checked:Boolean){
        Log.e("dnsn", "onStatusChanged: "+checked+noteItemViewModel.data.id)
        updateStatusUseCase(checked,noteItemViewModel.data.id).onEach {
           when(it.action){
               Action.SUCCESS->{
                   Log.e("mssh1", "onStatusChanged: "+"Success" )
               }
           }
        }.launchIn(viewModelScope)


    }


    private val _datas=MutableLiveData<Events<com.example.core.module.Notes>>()
    public val datas:LiveData<Events<com.example.core.module.Notes>> =_datas

    private fun onNoteItemClick(noteItemViewModel: NoteItemViewModel) {

        moveToAddFragmentUseCase.invoke().onEach {
            when(it.action){
                Action.SUCCESS->{

                }
                Action.Go_To_NewPage-> {
                    _datas.value= Events(noteItemViewModel.data)
                }
            }

        }.launchIn(viewModelScope)


    }



    private val _datasdelete=MutableLiveData<Events<com.example.core.module.Notes>>()
     val datasdelete:LiveData<Events<com.example.core.module.Notes>> =_datasdelete
    private fun onDeleteClick(noteItemViewModel: NoteItemViewModel) {
        Log.e("ids", "onDeleteClick: "+noteItemViewModel.data.id )

        itemDeleteUseCase(noteItemViewModel.data.id).onEach {
            when(it.action){
                Action.ASK->{
                    _askNotify.value = Events(it.target)
                }
            }

        }.launchIn(viewModelScope)


//        deleteUseCase.invoke(noteItemViewModel.data).onEach {
//            when(it.action){
//                Action.SUCCESS->{
//
//                    readdata()
//
//                }
//            }
//        }.launchIn(viewModelScope)


    }
    private fun populateItems(){

        getAllNotesUseCase().onEach {
            when(it.action){
                Action.SHOW_FETCH_ERROR->{
                    Log.e("failed", "it.target" )
                }
                Action.POPULATE_ITEMS->{
                    notesData=  it.target!!
                }
            }
            val viewData= mutableListOf<NoteItemViewModel>()
            Log.e("klist", ": "+notesData.size.toString() )

            addRestaurantItem(viewData,notesData)
            try {
                _noteItems.postValue(viewData)
            }catch (e:Exception){
                Log.e("excecp", ": "+e.toString() )
            }

        }.launchIn(viewModelScope)





    }

    fun onAskAnswered(askData: AskData, answer: ANSWER) {
        Log.e("checkinganswer", "onAskAnswered: "+answer )
        askAnsweredUseCase(askData,answer).onEach {
            when(it.action){
                Action.POPULATE_ITEMS->{
                    notesData=  it.target!!
                }
            }
            val viewData= mutableListOf<NoteItemViewModel>()
            Log.e("klist", ": "+notesData.size.toString() )

            addRestaurantItem(viewData,notesData)
            try {
                _noteItems.postValue(viewData)
            }catch (e:Exception){
                Log.e("excecp", ": "+e.toString() )
            }



        }.launchIn(viewModelScope)
    }


}