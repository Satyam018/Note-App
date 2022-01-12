package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.module.Notes


@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String,
    var body:String,
    var status:Boolean
) {

    val toCoreNotes:com.example.core.module.Notes
    get() {
        return Notes(id,title,body,status)
    }
}