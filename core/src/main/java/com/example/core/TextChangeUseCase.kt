package com.example.core

import android.os.Bundle
import com.example.core.module.Command
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TextChangeUseCase @Inject constructor() {

    operator fun invoke(bundle: Bundle)= flow {
        when(bundle.getInt("id")){
            -1->{
                emit(Command(Action.TEXT,"Create New Notes"))
            }else->{
                emit(Command(Action.TEXT,"Edit Notes"))
            }
        }

    }
}