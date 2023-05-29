package com.jomi.weitstudy.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverShopService {
    @GET("v1/search/shop.json")
    fun getSearchShop(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null,
        @Query("sort") sort : String? = null,
        @Query("filter") filter : String? = null,
        @Query("exclude") exclude : String? = null
    ) : Call<NaverShopResponse>
}