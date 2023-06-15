package com.jomi.weitstudy.network.Room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_item_table")
data class LikeItem(
    @PrimaryKey(autoGenerate = false)
    var productId: String,
    val lprice: String,
    val mallName: String,
    val title: String,

    var isLike : Boolean = false,
    var numLike : Int = 0
)
