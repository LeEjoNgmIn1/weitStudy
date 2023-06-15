package com.jomi.weitstudy.network.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jomi.weitstudy.network.model.LikeShopItem

@Database(entities = [LikeShopItem::class], version = 1)
abstract class LikeItemDatabase : RoomDatabase() {
    abstract fun likeItemDao() : LikeItemDao

    companion object{
        private var instance : LikeItemDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : LikeItemDatabase? {
            if(instance == null){
                synchronized(LikeItemDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LikeItemDatabase::class.java,
                        "likeitem-database"
                    ).build()
                }
            }
            return instance
        }
    }
}