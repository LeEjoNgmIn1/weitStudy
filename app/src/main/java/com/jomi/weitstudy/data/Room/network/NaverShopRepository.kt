package com.jomi.weitstudy.data.Room.network


import com.jomi.weitstudy.data.model.NaverShopResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class NaverShopRepository @Inject constructor(private val naverShopService : NaverShopService){

    suspend fun naverShopSearch(query: String, display: Int, start: Int) : ApiResponse<NaverShopResponse> =
        naverShopService.getSearchShop(query, display, start)

}


