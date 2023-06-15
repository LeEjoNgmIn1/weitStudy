package com.jomi.weitstudy.network.Room

import androidx.room.Dao
import androidx.room.Insert
import com.jomi.weitstudy.network.model.LikeShopItem

@Dao
interface LikeItemDao {
    @Insert
    fun insert(likeShopItem : LikeShopItem)
}