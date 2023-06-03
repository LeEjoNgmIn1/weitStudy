package com.jomi.weitstudy.network.model

import com.jomi.weitstudy.network.model.NaverShopItem
import com.squareup.moshi.Json

data class NaverShopResponse(
    @field:Json(name = "display")
    val display: Int,
    @field:Json(name = "items")
    val items: List<NaverShopItem>?,
    @field:Json(name = "lastBuildDate")
    val lastBuildDate: String,
    @field:Json(name = "start")
    val start: Int,
    @field:Json(name = "total")
    val total: Int
)

