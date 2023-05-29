package com.jomi.weitstudy.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val naverShopService : NaverShopService){

    fun makeApiCall(liveDataList: MutableLiveData<List<Item>>){
        val call: Call<NaverShopResponse> = naverShopService.getSearchShop("1x2aTgZVkK7svKJMvV3Y", "omjUXekf98", "가방")
        call?.enqueue(object : Callback<NaverShopResponse>{
            override fun onResponse(
                call: Call<NaverShopResponse>,
                response: Response<NaverShopResponse>
            ) {
                liveDataList.postValue(response.body()?.items!! as List<Item>?)
            }

            override fun onFailure(call: Call<NaverShopResponse>, t: Throwable) {
                Log.d("Tag", t.message.toString())
                liveDataList.postValue(null)
            }
        })
    }
}


