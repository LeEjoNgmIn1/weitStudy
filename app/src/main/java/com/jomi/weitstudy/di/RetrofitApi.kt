package com.jomi.weitstudy.di

import com.jomi.weitstudy.network.NaverShopResponse
import com.jomi.weitstudy.network.NaverShopService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitApi {

    const val BASE_URL = "https://openapi.naver.com/"

    private val okHttpClient: OkHttpClient by lazy{
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun getRetroServiceInstance(retrofit: Retrofit): NaverShopService{
        return getRetroInstance().create(NaverShopService::class.java)
    }

}