package com.example.news_reader

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SearchResultActivity : AppCompatActivity() {
    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val query = intent.getStringExtra("query").toString() ?: return
        val lv_news = findViewById<ListView>(R.id.LV_resultNews_SR)
        val btn_search = findViewById<ImageButton>(R.id.btn_search_SR)
        val ET_search = findViewById<TextView>(R.id.ET_search_SR)

        // First Search
        performSearch(lv_news, query)
        Log.d("qeury: ", query)

        // Re-search
        btn_search.setOnClickListener{
            val newSearch = ET_search.text.toString()
            performSearch(lv_news,newSearch)
        }

        lv_news.setOnItemClickListener { adapterView, view, position, l ->
            val selectedNews = viewModel.searchResults.value?.get(position)
            selectedNews?.let{
                val intent = Intent(this, NewsDetailActivity::class.java)
                intent.putExtra("news", it)
                startActivity(intent)
            }
        }

    }

    private fun performSearch( lv_news: ListView, query: String){
        viewModel.searchNews(query)
        viewModel.searchResults.observe(this){searchResults->
            val adapter = newsAdapter(this, searchResults)
            lv_news.adapter = adapter
        }
    }
}