package com.example.news_reader

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (news:News)

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>
}