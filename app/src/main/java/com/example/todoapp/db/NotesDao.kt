package com.example.todoapp.db

import android.accounts.Account
import androidx.room.*
import com.example.todoapp.model.Notes


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    suspend fun getAllNotes():List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Query("DELETE FROM notes_table WHERE id=:id")
    suspend fun deleteNotes(id:Int)

    @Query("UPDATE notes_table SET status=:statuss WHERE id=:ids")
    suspend fun updateStatus(statuss:Boolean,ids:Int)


    @Query("UPDATE notes_table SET status=:statuss,title=:title,body=:body WHERE id=:id")
    suspend fun updateAll(statuss: Boolean,id: Int,title:String,body:String)








}