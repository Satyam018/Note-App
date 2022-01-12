package com.example.todoapp

import com.example.core.TextIdentity
import com.example.core.TextProvider

class TextProviderImpl: TextProvider {
    override fun get(id: TextIdentity): String {
        return when(id){
            TextIdentity.APP_NAME -> R.string.app_name.string()
            TextIdentity.ARE_YOU_SURE_TO_DELETE -> R.string.are_you_sure_to_delete.string()
            TextIdentity.YES -> R.string.yes.string()
            TextIdentity.NO -> R.string.no.string()
        }
    }
}