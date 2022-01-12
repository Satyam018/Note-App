package com.example.core.module

import com.example.core.Action

data class Command<E>(
    val action: Action,
    val target:E?=null
)
