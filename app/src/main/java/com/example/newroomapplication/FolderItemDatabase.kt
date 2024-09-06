package com.example.newroomapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class FolderItemDatabase: RoomDatabase() {

    abstract fun taskItemDao(): TaskItemDao

    companion object
    {

        @Volatile
        private var INSTANCE: FolderItemDatabase? = null

        fun getDatabase(context: Context): FolderItemDatabase
        {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FolderItemDatabase::class.java,
                    "folder"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}