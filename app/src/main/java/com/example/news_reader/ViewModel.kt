package com.example.news_reader

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository

    val allNews: LiveData<List<News>>

    init {
        val newsDao = NewsDatabase.getDatabase(application).newsDao()
        val newsApiService = NewsApiService.create()
        repository = NewsRepository(newsDao, newsApiService)
        allNews = repository.allNews
    }

    fun searchNews( query: String, callback: (List<News>)->Unit ){
        viewModelScope.launch {
            val result = repository.searchNews(query)
            callback(result)
        }
    }
}

class SearchResultViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository

    private val _searchResults = MutableLiveData<List<News>>()
    val searchResults: LiveData<List<News>> = _searchResults

    init {
        val newsDao = NewsDatabase.getDatabase(application).newsDao()
        val newsApiService = NewsApiService.create()
        repository = NewsRepository(newsDao, newsApiService)
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            val results = repository.searchNews(query)
            _searchResults.postValue(results)
        }
    }
}

class NewsDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).newsDao()
        val newsApiService = NewsApiService.create()
        repository = NewsRepository(newsDao, newsApiService)
    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            repository.insert(news)
        }
    }
}

class SavedListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository

    val allSavedNews: LiveData<List<News>>

    init {
        val newsDao = NewsDatabase.getDatabase(application).newsDao()
        val newsApiService = NewsApiService.create()
        repository = NewsRepository(newsDao, newsApiService)
        allSavedNews = repository.allNews
    }
}