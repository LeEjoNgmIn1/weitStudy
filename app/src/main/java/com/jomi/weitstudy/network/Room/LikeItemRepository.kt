package com.jomi.weitstudy.network.Room

import androidx.lifecycle.LiveData

class LikeItemRepository(private val likeItemDao: LikeItemDao) {

    val raedAllLikeItemData : LiveData<List<LikeShopItem>> = likeItemDao.readAllLikeItem()

    suspend fun addLikeItem(likeShopItem: LikeShopItem){
        likeItemDao.addLikeItem(likeShopItem)
    }
}