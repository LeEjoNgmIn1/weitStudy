package com.jomi.weitstudy.network.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikeItemDao {
    @Insert
    suspend fun addLikeItem(likeShopItem : LikeShopItem)

    @Query("SELECT * FROM like_shop_Item")
    fun readAllLikeItem() : LiveData<List<LikeShopItem>>
}