package com.example.core

import com.example.core.module.ANSWER_TYPE
import com.example.core.module.AskData
import com.example.core.module.Command
import com.example.core.module.QUESTION
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemDeleteUseCase @Inject constructor(
    val textProvider: TextProvider
) {

    operator fun invoke(itemId: Int)= flow {
        emit(Command(Action.ASK,AskData(
            id = QUESTION.ARE_YOU_SURE_TO_DELETE,
            question = textProvider[TextIdentity.ARE_YOU_SURE_TO_DELETE],
            answerType = ANSWER_TYPE.YES_NO_CANCEL,
            corresponds = itemId
        )))
        //emit(Command(Action.Go_To_NewPage,Pages.DIALOGUE_FRAGMENT))
    }
}