package com.example.core.module
data class Notes(
    var id:Int,
    var title:String,
    var Body:String,
    var status:Boolean

) {
    fun isEqual(data:Notes):Boolean{
        return id==data.id
    }
}