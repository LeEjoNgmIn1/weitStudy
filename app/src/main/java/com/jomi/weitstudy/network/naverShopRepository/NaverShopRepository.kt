package com.jomi.weitstudy.network.naverShopRepository


import com.jomi.weitstudy.network.model.NaverShopResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class NaverShopRepository @Inject constructor(private val naverShopService : NaverShopService){

    suspend operator fun invoke(query: String, display: Int, start: Int) : ApiResponse<NaverShopResponse> =
        naverShopService.getSearchShop(query, display, start)

    suspend fun naverShopSearch(query: String, display: Int, start: Int) : ApiResponse<NaverShopResponse> =
        naverShopService.getSearchShop(query, display, start)

}


