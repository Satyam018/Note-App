package com.example.core

interface TextProvider {
    operator fun get(id: TextIdentity): String
}

enum class TextIdentity{
    APP_NAME,
    ARE_YOU_SURE_TO_DELETE,
    YES,
    NO,
}