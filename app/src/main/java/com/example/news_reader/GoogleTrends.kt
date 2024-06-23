package com.example.news_reader

import okhttp3.OkHttpClient

class GoogleTrends {
    private val client = OkHttpClient()

    fun getGTKeyworld() : List<String> {
      //return listOf("엘든링", "트위터", "북한군 dmz 지뢰", "러브버그", "푸틴")
        return listOf("putin", "twiter", "dmz", "lovebug")
    }
}