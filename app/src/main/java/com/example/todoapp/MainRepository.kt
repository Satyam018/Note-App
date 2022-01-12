package com.example.todoapp

import com.example.core.Resource
import com.example.core.module.MainRepositoryInterface
import com.example.todoapp.db.NotesDao
import com.example.todoapp.model.Notes
import javax.inject.Inject

class MainRepository @Inject constructor(
    val notesDao: NotesDao
):MainRepositoryInterface {

    override suspend fun getNotes():Resource<List<com.example.core.module.Notes>>{
        val datas=notesDao.getAllNotes()
       return Resource.Success(data = datas.map {
           it.toCoreNotes
       })
    }
    override suspend fun insertNotes(heading:String,body:String,status:Boolean):Resource<String>{
        notesDao.insertNotes(Notes(title = heading,body= body,status= status))
        return Resource.Success("Success")
    }

    override suspend fun getDelete(id:Int): Resource<String> {
        notesDao.deleteNotes(id)
        return Resource.Success("Success")
    }
    override suspend fun updateStatus(status: Boolean,id:Int):Resource<String>
    {
        notesDao.updateStatus(statuss = status,ids=id)
        return Resource.Success("Success")


    }
    override suspend fun updateAll(status: Boolean,id: Int,heading: String,body: String):Resource<String>{
        notesDao.updateAll(status,id,heading,body)
        return Resource.Success("Success")

    }

}