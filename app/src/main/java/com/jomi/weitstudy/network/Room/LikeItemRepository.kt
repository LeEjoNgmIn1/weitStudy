package com.jomi.weitstudy.network.Room

import javax.inject.Inject

class LikeItemRepository @Inject constructor(
    private val likeItemDao : LikeItemDao
) {
    suspend fun addLikeItem(likeItem: LikeItem) = likeItemDao.addLikeItem(likeItem)

    suspend fun deleteLikeItem(likeItem: LikeItem) = likeItemDao.deleteLikeItem(likeItem)
}

