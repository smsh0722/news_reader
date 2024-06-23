package com.example.news_reader

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {
    @Headers(
        "X-Naver-Client-Id: EmQoue0ocvLrT0V8waQk",
        "X-Naver-Client-Secret: gJjC6g5pJ7"
    )

    @GET("v1/search/news.json")
    suspend fun searchNews(@Query("query") query: String) : NewsResponse

    companion object{
        private const val BASE_URL = "https://openapi.naver.com/"

        fun create(): NewsApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NewsApiService::class.java)
        }
    }
}