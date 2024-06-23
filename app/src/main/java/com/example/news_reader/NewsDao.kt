package com.example.news_reader

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert
    suspend fun insert (news:News)

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>
}