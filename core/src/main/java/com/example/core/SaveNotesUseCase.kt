package com.example.core

import android.os.Bundle
import com.example.core.module.Command
import com.example.core.module.MainRepositoryInterface
import com.example.core.module.Notes
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveNotesUseCase @Inject constructor(
    val mainRepository: MainRepositoryInterface
) {
    operator fun invoke (heading:String,body:String,bundle: Bundle)= flow {
        when(bundle.getInt("id")){
            -1->{
                var r= mainRepository.insertNotes(heading,body,false)
                emit(Command(Action.Go_To_NewPage,Pages.HOME_FRAGMENT))
            }
            else->{
                var r=mainRepository.updateAll(false,bundle.getInt("id"),heading,body)
                emit(Command(Action.Go_To_NewPage,Pages.HOME_FRAGMENT))

    }}

}
}