package com.jomi.weitstudy.network

import javax.inject.Inject

class NaverShopRepository @Inject constructor(private val naverShopService : NaverShopService){

    suspend fun naverShopSearch(query: String, display: Int, start: Int) =
        naverShopService.getSearchShop(query, display, start)
    
}


