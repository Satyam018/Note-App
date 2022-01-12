package com.example.core

import android.provider.ContactsContract
import com.example.core.module.Command
import com.example.core.module.MainRepositoryInterface
import com.example.core.module.Notes
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    val mainRepositoryInterface: MainRepositoryInterface
) {

    operator fun invoke()= flow {
       val notes= mainRepositoryInterface.getNotes().data
           if (notes==null){
               emit(Command(Action.SHOW_FETCH_ERROR,null))
           }else{
               emit(Command(Action.POPULATE_ITEMS,notes))
           }


    }
}