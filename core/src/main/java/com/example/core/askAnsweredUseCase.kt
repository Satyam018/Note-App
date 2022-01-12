package com.example.core

import com.example.core.module.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AskAnsweredUseCase @Inject constructor(
      val  mainRepositoryInterface: MainRepositoryInterface
) {
    operator fun invoke(askData: AskData,answer:ANSWER)= flow {
        when(askData.id){
            QUESTION.ARE_YOU_SURE_TO_DELETE -> {
                when(answer){
                    ANSWER.YES -> {
                        //delete
                        //get the latest list
                        //emit
                        mainRepositoryInterface.getDelete(askData.corresponds as Int)
                        val datas=mainRepositoryInterface.getNotes()
                        emit(Command(Action.POPULATE_ITEMS,datas.data))

                    }
                    ANSWER.NO -> {
                        //skip explicitly
                    }
                    ANSWER.CANCEL -> {
                        //skip explicitly
                    }
                }
            }
        }
    }
}