package com.jomi.weitstudy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_item_table")
data class LikeItems(
    @PrimaryKey(autoGenerate = false)
    val productId: String,
    val lprice: String,
    val mallName: String,
    val image: String,
    val title: String,

//    var numLike : Int = 0
)
