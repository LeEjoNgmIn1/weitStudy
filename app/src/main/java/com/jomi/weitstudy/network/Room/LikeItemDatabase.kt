package com.jomi.weitstudy.network.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [LikeItem::class],
    version = 1,
    exportSchema = false
)

abstract class LikeItemDatabase : RoomDatabase() {
    abstract fun likeItemDao() : LikeItemDao
}