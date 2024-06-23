package com.example.news_reader

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class],  version = 1 )
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile private var instance : NewsDatabase ?= null

        fun getDatabase(context: Context): NewsDatabase =
            instance ?: synchronized(this){
                instance ?: buildDatabase(context).also{ instance = it }
            }
        private fun buildDatabase( context: Context ) =
            Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "news.db").build()
    }
}