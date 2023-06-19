package com.jomi.weitstudy.data.Room

import androidx.lifecycle.LiveData
import com.jomi.weitstudy.data.model.LikeItem


interface LikeItemRepository{

    suspend fun addLikeItem(likeItem: LikeItem)

    suspend fun deleteLikeItem(likeItem: LikeItem)

    suspend fun getLikeItem(): LiveData<List<LikeItem>>

    suspend fun getLikeItemId() : List<String>

    suspend fun isLikeItem(productId : String): Boolean
}
