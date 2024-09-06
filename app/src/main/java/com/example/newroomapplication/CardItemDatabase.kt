package com.example.newroomapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardItem::class], version = 2, exportSchema = false)
abstract class CardItemDatabase: RoomDatabase() {

    abstract fun cardItemDao(): CardItemDao

    companion object {
        @Volatile
        private var INSTANCE: CardItemDatabase? = null

        fun getDatabase(context: Context): CardItemDatabase{
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardItemDatabase::class.java,
                    "card"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}