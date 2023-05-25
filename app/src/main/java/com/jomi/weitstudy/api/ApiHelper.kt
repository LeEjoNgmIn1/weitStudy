package com.jomi.weitstudy.api

import com.jomi.weitstudy.models.NewsResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getNews(): Response<NewsResponse>
}