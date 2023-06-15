package com.jomi.weitstudy.network.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jomi.weitstudy.network.Room.LikeItem

@Dao
interface LikeItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLikeItem(likeItem: LikeItem)

    @Delete
    suspend fun deleteLikeItem(likeItem: LikeItem)

    @Query("SELECT * FROM like_item_table")
    suspend fun getLikeItem() : LiveData<List<LikeItem>>

    @Query("SELECT productId FROM like_item_table")
    suspend fun getLikeItemId() : List<String>

    @Query("SELECT * FROM like_item_table WHERE productId=id")
    suspend fun isLikeItem(id: String) : LikeItem?
}

//    OnConflictStrategy.ABORT	충돌이 발생할 경우 처리 중단
//    OnConflictStrategy.FAIL	충돌이 발생할 경우 실패처리
//    OnConflictStrategy.IGNORE	충돌이 발생할 경우 무시
//    OnConflictStrategy.REPLACE	충돌이 발생할 경우 덮어쓰기
//    OnConflictStrategy.ROLLBACK	충돌이 발생할 경우 이전으로 되돌리기