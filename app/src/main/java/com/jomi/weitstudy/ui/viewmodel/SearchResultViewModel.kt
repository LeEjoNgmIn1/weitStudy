package com.jomi.weitstudy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jomi.weitstudy.data.network.NaverShopRepository
import com.jomi.weitstudy.data.Room.LikeItemRepository
import com.jomi.weitstudy.data.model.LikeItems
import com.jomi.weitstudy.data.model.NaverShopItem
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val naverShopRepository: NaverShopRepository,
    private val likeItemRepository: LikeItemRepository) : ViewModel() {

    private var _naverShopResult : MutableLiveData<List<LikeItems>> = MutableLiveData()
    val naverShopResult : LiveData<List<LikeItems>> get() = _naverShopResult

    private var _ListPage : MutableLiveData<Int> = MutableLiveData(0)
    val Page : LiveData<Int> get() = _ListPage

    private var _naverShopApiResult : MutableList<LikeItems> = mutableListOf()

    private var _naverShopAllId : MutableList<String> = mutableListOf()
    
    private var searchJob: Job = Job().apply {
        cancel()
    }

    init {
        viewModelScope.launch {
            _naverShopAllId.addAll(likeItemRepository.getAllLikeId())
        }
    }



    private fun _searchNaverShop(query: String, page: Int = 0){
        searchJob = viewModelScope.launch {
            val response = naverShopRepository.naverShopSearch(query, PAGE_SIZE, page * PAGE_SIZE + 1)
            if(page == 0){
                _naverShopApiResult.clear()
            }

            response.onSuccess {

                for(i in 0 until PAGE_SIZE){
                    val temp = LikeItems(
                        data.items?.get(i)!!.productId,
                        data.items?.get(i)!!.lprice,
                        data.items?.get(i)!!.mallName,
                        data.items?.get(i)!!.image,
                        data.items?.get(i)!!.title
                    )
                    _naverShopApiResult.add(temp)
                }

                _naverShopResult.postValue(_naverShopApiResult.toList())
                _ListPage.value?.plus(PAGE_UP)

            }.onError {
                Log.d("jomi", "네트워크로 부터 에러응답을 내려받음")
            }.onException {
                Log.d("jomi", "네트워크로 응답을 받기 전/후에 예상치 못한 이유로 요청이 실패함")
            }

        }
    }


    fun searchNaverShop(query: String){
        if(searchJob.isCompleted) {
            _searchNaverShop(query)
        }
    }

    fun updateLikeItem(likeItems: LikeItems, isCheck: Boolean){
        viewModelScope.launch {
            if(isCheck){
                likeItemRepository.addLikeItem(likeItems)
            } else {
                likeItemRepository.deleteLikeItem(likeItems)
            }
        }
    }

    fun isLike(itemId: String) : Boolean {
        return _naverShopAllId.contains(itemId)
    }




    companion object{
        const val PAGE_SIZE = 20
        const val PAGE_UP = 1
    }
}

