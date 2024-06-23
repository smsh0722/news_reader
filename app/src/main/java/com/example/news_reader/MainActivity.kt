package com.example.news_reader

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private val googleTrendsVM = GoogleTrends()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ET_search = findViewById<EditText>(R.id.ET_search_main)
        val btn_search = findViewById<ImageButton>(R.id.btn_search_main)
        val btn_myFolder = findViewById<ImageButton>(R.id.btn_myFolder_main)
        val btn_reset = findViewById<ImageButton>(R.id.btn_restart_main)
        val LV_rankingNews = findViewById<ListView>(R.id.LV_resultNews_main)
        val items_news = mutableListOf<News>()
        val adapter = newsAdapter(this, items_news )
        LV_rankingNews.adapter = adapter

        // Get Trends
        searchTrends(items_news, adapter )

        btn_search.setOnClickListener{
            val query = ET_search.text.toString()
            val intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra("query", query)
            startActivity(intent)
        }
        btn_reset.setOnClickListener{
            searchTrends( items_news, adapter )
        }
        /*
        btn_myFolder.setOnClickListener {

        }*/

        LV_rankingNews.setOnItemClickListener { adapterView, view, position, l ->
            val selectedNews = viewModel.searchResults.value?.get(position)
            selectedNews?.let{
                val intent = Intent(this, NewsDetailActivity::class.java)
                intent.putExtra("news", it)
                startActivity(intent)
            }
        }
    }

    private fun searchTrends( items_news: MutableList<News>, adapter: newsAdapter ){
        items_news.clear()

        val trendsKeywords = googleTrendsVM.getGTKeyworld()

        viewModel.searchResults.observe(this, Observer { searchResults ->
            if (searchResults != null && searchResults.isNotEmpty()) {
                items_news.addAll(searchResults)
                adapter.notifyDataSetChanged()
            }
        })

        for (query in trendsKeywords) {
            viewModel.searchNews(query)
        }
    }
}