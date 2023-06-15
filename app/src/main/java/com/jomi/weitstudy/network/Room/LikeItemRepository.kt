package com.jomi.weitstudy.network.Room

import androidx.lifecycle.LiveData


interface LikeItemRepository{

    suspend fun addLikeItem(likeItem: LikeItem)

    suspend fun deleteLikeItem(likeItem: LikeItem)

    suspend fun getLikeItem(): LiveData<List<LikeItem>>

    suspend fun getLikeItemId() : List<String>

    suspend fun isLikeItem(productId : String): Boolean
}
