package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.Notes

@Database(entities = [Notes::class],version = 1)
abstract class NoteDatabase:RoomDatabase() {
        abstract fun notesDao():NotesDao

        companion object{
            private var Instance:NoteDatabase?=null

            fun getNoteDatabase(context: Context):NoteDatabase{

                if (NoteDatabase.Instance==null){
                Instance=Room.databaseBuilder(context,NoteDatabase::class.java,"NoteDB")
                    .build()


                }
                return Instance!!



            }


        }
}