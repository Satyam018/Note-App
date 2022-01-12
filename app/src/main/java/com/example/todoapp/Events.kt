package com.example.todoapp

class Events<out T>(private val content:T) {
    private var hasBeenHandled:Boolean=false
        fun getContentIfNotHandled()=if (!hasBeenHandled){
        hasBeenHandled=true
        content
        }else null

        fun peekContent():T=content
}