package com.jomi.weitstudy.network.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface LikeItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLikeItem(likeItem: LikeItem)

    @Delete
    suspend fun deleteLikeItem(likeItem: LikeItem)
}

//    OnConflictStrategy.ABORT	충돌이 발생할 경우 처리 중단
//    OnConflictStrategy.FAIL	충돌이 발생할 경우 실패처리
//    OnConflictStrategy.IGNORE	충돌이 발생할 경우 무시
//    OnConflictStrategy.REPLACE	충돌이 발생할 경우 덮어쓰기
//    OnConflictStrategy.ROLLBACK	충돌이 발생할 경우 이전으로 되돌리기