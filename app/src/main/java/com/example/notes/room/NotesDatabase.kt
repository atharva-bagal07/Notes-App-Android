package com.example.notes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NotesEntity::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun dao(): NotesDao

    companion object {

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
