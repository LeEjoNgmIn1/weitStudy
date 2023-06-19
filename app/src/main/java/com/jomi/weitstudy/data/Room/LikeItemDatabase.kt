package com.jomi.weitstudy.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jomi.weitstudy.data.model.LikeItem

@Database(
    entities = [LikeItem::class],
    version = 1,
    exportSchema = false
)

abstract class LikeItemDatabase : RoomDatabase() {
    abstract fun likeItemDao() : LikeItemDao
}