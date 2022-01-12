package com.example.core.module


import com.example.core.Resource

interface MainRepositoryInterface {

    suspend fun getNotes():Resource<List<Notes>>

    suspend fun insertNotes(heading:String,body:String,status:Boolean):Resource<String>

    suspend fun getDelete(id:Int):Resource<String>

     suspend fun updateStatus(status: Boolean,id:Int):Resource<String>

    suspend fun updateAll(status: Boolean,id: Int,heading: String,body: String):Resource<String>
}