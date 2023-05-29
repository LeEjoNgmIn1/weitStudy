package com.jomi.weitstudy.network

import com.squareup.moshi.Json

// recyclerList
data class NaverShopResponse(
    @field:Json(name = "display")
    val display: Int?,
    @field:Json(name = "items")
    val items: List<NaverShopItem?>?,
    @field:Json(name = "lastBuildDate")
    val lastBuildDate: String?,
    @field:Json(name = "start")
    val start: Int?,
    @field:Json(name = "total")
    val total: Int?
)

// recyclerData
data class NaverShopItem(
    @field:Json(name = "brand")
    val brand: String?,
    @field:Json(name = "category1")
    val category1: String?,
    @field:Json(name = "category2")
    val category2: String?,
    @field:Json(name = "category3")
    val category3: String?,
    @field:Json(name = "category4")
    val category4: String?,
    @field:Json(name = "hprice")
    val hprice: String?,
    @field:Json(name = "image")
    val image: String?,
    @field:Json(name = "link")
    val link: String?,
    @field:Json(name = "lprice")
    val lprice: String?,
    @field:Json(name = "maker")
    val maker: String?,
    @field:Json(name = "mallName")
    val mallName: String?,
    @field:Json(name = "productId")
    val productId: String?,
    @field:Json(name = "productType")
    val productType: String?,
    @field:Json(name = "title")
    val title: String?
)