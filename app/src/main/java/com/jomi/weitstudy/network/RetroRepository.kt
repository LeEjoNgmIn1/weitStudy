package com.jomi.weitstudy.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jomi.weitstudy.network.model.NaverShopItem
import com.jomi.weitstudy.network.model.NaverShopResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val naverShopService : NaverShopService){

    fun makeApiCall(query:String, display: Int, start: Int, liveDataList: MutableLiveData<List<NaverShopItem>>){
        val call: Call<NaverShopResponse> = naverShopService.getSearchShop(query, display, start)
        call?.enqueue(object : Callback<NaverShopResponse>{
            override fun onResponse(
                call: Call<NaverShopResponse>,
                response: Response<NaverShopResponse>
            ) {
                liveDataList.postValue(response.body()?.items!! as List<NaverShopItem>?)
            }

            override fun onFailure(call: Call<NaverShopResponse>, t: Throwable) {
                Log.d("Tag", t.message.toString())
                liveDataList.postValue(null)
            }
        })
    }
}


