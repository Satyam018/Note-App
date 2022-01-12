package com.example.todoapp.di

import android.content.Context
import com.example.core.TextProvider
import com.example.core.module.MainRepositoryInterface
import com.example.todoapp.MainRepository
import com.example.todoapp.TextProviderImpl
import com.example.todoapp.db.NoteDatabase
import com.example.todoapp.db.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context):NoteDatabase=
        NoteDatabase.getNoteDatabase(context = context)

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase):NotesDao=
        noteDatabase.notesDao()



    @Provides
    @Singleton
    fun providemainRepositoryInterface(noteDao: NotesDao):MainRepositoryInterface=MainRepository(noteDao)

    @Provides
    @Singleton
    fun provideTextProvider(): TextProvider {
        return TextProviderImpl()
    }

}