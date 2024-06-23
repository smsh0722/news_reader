package com.example.news_reader

import androidx.lifecycle.LiveData

class NewsRepository(private val newsDao: NewsDao, private val newsApiService: NewsApiService) {
    val allNews : LiveData<List<News>> = newsDao.getAllNews()

    suspend fun searchNews(query: String ): List<News>{
        return newsApiService.searchNews(query)
    }
    suspend fun insert(news:News){
        newsDao.insert(news)
    }
}