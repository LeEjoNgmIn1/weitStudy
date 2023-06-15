package com.jomi.weitstudy.network.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LikeShopItem::class], version = 1, exportSchema = false)
abstract class LikeItemDatabase : RoomDatabase() {
    abstract fun likeItemDao() : LikeItemDao

    companion object{
        // @Volatile = 접근가능한 변수의 값을 cache를 통해 사용하지 않고 thread가 직접 main memory에 접근 하게하여 동기화
        @Volatile
        private var instance : LikeItemDatabase? = null

        // 싱글톤으로 생성(자주 생성시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
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