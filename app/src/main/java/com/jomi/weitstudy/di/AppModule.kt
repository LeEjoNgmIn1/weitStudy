package com.jomi.weitstudy.di

import android.provider.SyncStateContract.Constants
import com.jomi.weitstudy.BuildConfig
import com.jomi.weitstudy.api.ApiHelper
import com.jomi.weitstudy.api.ApiHelperImpl
import com.jomi.weitstudy.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/*
8단계 AppModule 객체 생성
이제 필요한 인스턴스를 다른 클래스에 대한 종속성으로 제공하고 싱글톤 동작을 유지하는 모듈 개체를 만들 차례이다. 이제 다음과 같이 ApiModule개체를 만든다.

이 객체에는 단검 구현을 위한 모듈 개체임을 알려주는 "@Module" 주석이 있다.

다음으로 "@InstallIn(SingletonComponent)" 주석이 있다. 이 주석은 이 모듈이 일부 활동이나 프래그먼트뿐만 아니라 전체 애플리케이션수명 주기에 사용
할 수 있음을 알려준다. SingletonComponent클래스와 마찬가지로 활동 및 프래그먼트 수명 주기 범위에 대한 ActivityComponent 및 FragmentComponent
클래스도 있다.

이제 이 개체 내부에 "@Singleton" 및 "@Provides" 주석이 있는 provideRetrofit(), provideOkhttpClient()등과 같은 일부 함수를 정의 했다.
이러한 주석은 해당 특정 클래스의 단일 인스턴스만 응용 프로그램 전체에서 사용할 수 있으며 필요할 때마다 종속성으로 전달될 것임을 알려준다.

 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = com.jomi.weitstudy.others.Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl) : ApiHelper = apiHelper
}