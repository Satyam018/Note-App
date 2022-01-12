package com.example.core

import com.example.core.module.Command
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoveToAddFragmentUseCase @Inject constructor(){


    operator fun invoke()= flow {
        emit(Command(Action.Go_To_NewPage,Pages.ADD_FRAGEMENT))
    }
}