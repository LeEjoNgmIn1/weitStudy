package com.jomi.weitstudy.data.Room

import com.jomi.weitstudy.data.model.LikeItems
import com.jomi.weitstudy.data.model.NaverShopResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class LikeItemRepository @Inject constructor(
    private val likeItemDao: LikeItemDao
){
    suspend fun addLikeItem(likeItems: LikeItems){
        likeItemDao.addLikeItem(likeItems)
    }

    suspend fun deleteLikeItem(likeItems: LikeItems){
        likeItemDao.deleteLikeItem(likeItems)
    }

    suspend fun getAllLikeId() : List<String>{
        return likeItemDao.getAllLikeId()
    }

    suspend fun getAllLikeItemData(): List<LikeItems>{
        return likeItemDao.getAllLikeItemData()
    }

}





