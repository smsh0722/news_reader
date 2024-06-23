package com.example.news_reader

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class NewsRepository(private val newsDao: NewsDao, private val newsApiService: NewsApiService) {
    val allNews : LiveData<List<News>> = newsDao.getAllNews()

    suspend fun searchNews(query: String ): List<News>{
        return newsApiService.searchNews(query).items
    }
    suspend fun insert(news:News){
        withContext(Dispatchers.IO) {
            newsDao.insert(news)
        }
    }
}