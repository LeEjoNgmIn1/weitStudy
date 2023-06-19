package com.jomi.weitstudy.data.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jomi.weitstudy.data.model.LikeItems

@Dao
interface LikeItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLikeItem(likeItem: LikeItems)

    @Delete
    suspend fun deleteLikeItem(likeItem: LikeItems)

    @Query("SELECT * FROM like_item_table")
    suspend fun getAllLikeItemData() : List<LikeItems>

    @Query("SELECT productId FROM like_item_table")
    suspend fun getAllLikeId() : List<String>
}

