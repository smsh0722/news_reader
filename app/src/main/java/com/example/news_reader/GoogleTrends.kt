package com.example.news_reader

import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONObject
import org.jsoup.Jsoup
import org.w3c.dom.Document

class GoogleTrends {
    private val client = OkHttpClient()

    fun getGTKeyworld() : List<String> {
        val url = "https://trends.google.co.kr/trends/trendingsearches/daily?geo=KR&hl=ko"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        if ( !response.isSuccessful ) throw IOException(
            "Error: Request to google: $response"
        )

        val htmlResponse =  response.body?.string()
        val doc: org.jsoup.nodes.Document? = Jsoup.parse(htmlResponse)
        val trendsSearches = doc?.select(".details-top a span")

        val keywords = mutableListOf<String>()
        if (trendsSearches != null) {
            for ( keyword in trendsSearches ) {
                keywords.add(keyword.text())
            }
        }

        return keywords
    }
}