package com.jomi.weitstudy.network.Room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeItemRepositoryImpl @Inject constructor(
    private val db : LikeItemDatabase
) : LikeItemRepository {
    override suspend fun addLikeItem(likeItem: LikeItem) {
        db.likeItemDao().addLikeItem(likeItem)
    }

    override suspend fun deleteLikeItem(likeItem: LikeItem) {
        db.likeItemDao().deleteLikeItem(likeItem)
    }

    override suspend fun getLikeItem(): LiveData<List<LikeItem>> {
        return db.likeItemDao().getLikeItem()
    }

    override suspend fun getLikeItemId(): List<String> {
        return db.likeItemDao().getLikeItemId()
    }

    override suspend fun isLikeItem(productId: String): Boolean {
        return withContext(Dispatchers.IO){
            db.likeItemDao().isLikeItem(productId) != null
        }
    }
}