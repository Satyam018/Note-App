package com.example.core

import com.example.core.module.Command
import com.example.core.module.MainRepositoryInterface
import com.example.core.module.Notes
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    val mainRepositoryInterface: MainRepositoryInterface
) {

    operator fun invoke(id:Int)= flow {
        mainRepositoryInterface.getDelete(id)
        emit(Command(Action.SUCCESS,null))
    }
}