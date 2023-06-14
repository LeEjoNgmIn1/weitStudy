package com.jomi.weitstudy.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_shop_Item")
data class LikeShopItem(
    @PrimaryKey(autoGenerate = true)
    var productId: String,
    var isLike : Boolean,
    var numLike : String
)
