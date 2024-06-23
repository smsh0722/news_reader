package com.example.news_reader

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

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
        val LV_rankingNews = findViewById<ListView>(R.id.LV_resultNews_main)

        btn_search.setOnClickListener{
            val query = ET_search.text.toString()
            viewModel.searchNews(query)
        }

        btn_myFolder.setOnClickListener {

        }

        LV_rankingNews.setOnClickListener {

        }
    }
}