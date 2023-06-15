package com.jomi.weitstudy.network.Room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLikeItemDataBase(@ApplicationContext context: Context) : LikeItemDatabase =
        Room.databaseBuilder(
            context,
            LikeItemDatabase::class.java,
            "like_item_table"
        ).build()


    @Provides
    fun provideLikeItemDao(likeItemdatabase: LikeItemDatabase) : LikeItemDao = likeItemdatabase.likeItemDao()
}