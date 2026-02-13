package com.example.notes.dependencyInjection

import android.app.Application

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.initializeDB(context = this)
    }
}