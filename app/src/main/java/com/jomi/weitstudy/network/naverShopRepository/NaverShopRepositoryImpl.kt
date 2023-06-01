package com.jomi.weitstudy.network.naverShopRepository

import com.jomi.weitstudy.network.NaverShopService
import com.jomi.weitstudy.network.model.NaverShopResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class NaverShopRepositoryImpl @Inject constructor(private val naverShopService: NaverShopService)
//    : NaverShopRepository{
//    override suspend fun NaverShopSearch(
//        query: String,
//        display: Int,
//        start: Int
//    ): Response<NaverShopResponse> {
//        return naverShopService.getSearchShop(query, display, start)
//    }
//}