package com.jomi.weitstudy.di

import android.content.Context
import androidx.room.Room
import com.jomi.weitstudy.BuildConfig
import com.jomi.weitstudy.data.network.NaverShopRepository
import com.jomi.weitstudy.data.network.NaverShopService
import com.jomi.weitstudy.data.Room.LikeItemDao
import com.jomi.weitstudy.data.Room.LikeItemDatabase
import com.jomi.weitstudy.others.Constants
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/*
모듈 어노테이션이 존재하는 이유는 다음과 같다.
    - 우리가 의존성을 주입할 때 외부라이브러리는 Hilt가 인스턴스를 생성하지 못하는 경우가 있다.
    - 해당 인스턴스는 어떻게 생성해야 하는지 개발자가 알려줘야 한다.

위와 같은 이유 때문에 우리는 @Module을 사용해서 인스턴스를 만들고 Hilt가 의존성을 주입할 때 생성자를 생성하는 방법을 모를때 어떻게
생성하는지 가이드라인을 제공하는 것이다.

이렇게 가이드라인을 생성하고 나면 우리는 이 모듈을 어떤 컴포넌트(컨테이너)에 제공할 것인지 정해야한다. 이번에는 Retrofit에 관한
모듈이니까 Singleton Component를 사용 하겠다.


 */
@Module
@InstallIn(SingletonComponent::class)
object Modules {

    private const val BASE_URL = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkhttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNaverShopService(retrofit: Retrofit): NaverShopService {
        return retrofit.create(NaverShopService::class.java)
    }

    @Provides
    @Singleton
    fun provideNaverShopRepository(naverShopService: NaverShopService) = NaverShopRepository(naverShopService)

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