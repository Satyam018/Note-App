package com.example.core

import android.util.Log
import com.example.core.module.Command
import com.example.core.module.MainRepositoryInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateStatusUseCase @Inject constructor(
    val mainRepositoryInterface: MainRepositoryInterface
) {

    operator fun invoke(status:Boolean,id:Int)= flow {
        Log.e("ddd", "invoke: "+status+id )
        mainRepositoryInterface.updateStatus(status = status,id=id)
        emit(Command(Action.SUCCESS,null))
        emit(Command(Action.Go_To_NewPage,null))

    }
}