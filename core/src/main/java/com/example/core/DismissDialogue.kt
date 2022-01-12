package com.example.core

import com.example.core.module.Command
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DismissDialogue @Inject constructor() {
    operator fun invoke()= flow{
        emit(Command(Action.DISMISS,Pages.DIALOGUE_FRAGMENT))
    }
}