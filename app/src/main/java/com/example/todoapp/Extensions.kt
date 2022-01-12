package com.example.todoapp

import com.example.core.TextProvider
import com.example.core.module.ANSWER
import com.example.todoapp.di.App

fun Int.string(): String
{
    return App.instance.stringResource(this)
}

fun String.toAnswer(): ANSWER {
    return when(this){
        R.string.yes.string()->{
            ANSWER.YES
        }
        R.string.no.string()->{
            ANSWER.NO
        }
        R.string.cancel.string()->{
            ANSWER.CANCEL
        }
        else -> ANSWER.CANCEL
    }
}