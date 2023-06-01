package com.jomi.weitstudy.network

import javax.inject.Inject

class NaverShopRepository @Inject constructor(private val naverShopService : NaverShopService){

    suspend fun naverShopSearch(query: String, display: Int, start: Int) =
        naverShopService.getSearchShop(query, display, start)


//    fun makeApiCall(query:String, display: Int, start: Int, liveDataList: MutableLiveData<List<NaverShopItem>>){
//        val call: Call<NaverShopResponse> = naverShopService.getSearchShop(query, display, start)
//        call?.enqueue(object : Callback<NaverShopResponse>{
//            override fun onResponse(
//                call: Call<NaverShopResponse>,
//                response: Response<NaverShopResponse>
//            ) {
//                liveDataList.postValue(response.body()?.items!! as List<NaverShopItem>?)
//            }
//
//            override fun onFailure(call: Call<NaverShopResponse>, t: Throwable) {
//                Log.d("Tag", t.message.toString())
//                liveDataList.postValue(null)
//            }
//        })
//    }
}


