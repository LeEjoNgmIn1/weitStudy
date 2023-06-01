package com.jomi.weitstudy.network

import com.jomi.weitstudy.network.model.NaverShopResponse
import com.jomi.weitstudy.others.Constants.CLIENT_ID
import com.jomi.weitstudy.others.Constants.CLIENT_SECRET
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverShopService {
    @Headers(
        "X-Naver-Client-Id: $CLIENT_ID",
        "X-Naver-Client-Secret: $CLIENT_SECRET"
    )
    @GET("v1/search/shop.json")
    fun getSearchShop(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ) : Call<NaverShopResponse>
}