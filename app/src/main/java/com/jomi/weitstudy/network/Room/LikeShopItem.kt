package com.jomi.weitstudy.network.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_shop_Item")
data class LikeShopItem(
    @PrimaryKey(autoGenerate = false)
    var productId: String,
    val image: String,
    val mallName: String,
    val title: String,
    val lprice: String,


    var isLike : Boolean,
    var numLike : Int
)
