package com.example.todoapp.di

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun stringResource(@StringRes stringId: Int):String
    {
        return try {
            resources.getString(stringId)
        } catch (e: Exception) {
            ""
        }
    }
}