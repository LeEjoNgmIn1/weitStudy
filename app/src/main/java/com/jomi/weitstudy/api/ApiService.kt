package com.jomi.weitstudy.api

import com.jomi.weitstudy.models.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {
    @GET("v1/search/news.json")
    fun getSearchNews(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    )
    suspend fun getNews(
    ): Response<NewsResponse>
}