package com.example.core.module

import com.example.core.TextProvider

enum class ANSWER_TYPE{
    YES_NO,
    YES_NO_CANCEL,
    STRING,
    //etc
}
enum class QUESTION{
    ARE_YOU_SURE_TO_DELETE
}
enum class ANSWER{
    YES,
    NO,
    CANCEL
}

data class AskData(
    val id: QUESTION,
    val question: String,
    val answerType: ANSWER_TYPE,
    val corresponds: Any
)
