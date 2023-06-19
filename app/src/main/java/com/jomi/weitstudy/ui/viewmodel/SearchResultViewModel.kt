package com.jomi.weitstudy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jomi.weitstudy.data.network.NaverShopRepository
import com.jomi.weitstudy.data.Room.LikeItemRepository
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

    private var _naverShopResult : MutableLiveData<List<NaverShopItem>> = MutableLiveData()
    val naverShopResult : LiveData<List<NaverShopItem>> get() = _naverShopResult

    private var _naverShopListPage : MutableLiveData<Int> = MutableLiveData(0)
    val naverShopListPage : LiveData<Int> get() = _naverShopListPage

    private var _naverShopApiResult : MutableList<NaverShopItem> = mutableListOf()
    
    private var searchJob: Job = Job().apply {
        cancel()
    }



    private fun _searchNaverShop(swipeRefreshLayout: SwipeRefreshLayout, query: String = "", page: Int = 0){
        searchJob = viewModelScope.launch {
            val response = naverShopRepository.naverShopSearch(query, PAGE_SIZE, page * PAGE_SIZE + 1)
            if(page == 0){
                _naverShopApiResult.clear()

                if(swipeRefreshLayout.isRefreshing) {swipeRefreshLayout.isRefreshing = false}
            }

            response.onSuccess {
                val temp = data.items
                temp?.let{_naverShopApiResult.addAll(it)}

                _naverShopResult.postValue(_naverShopApiResult.toList())
                _naverShopListPage.value = _naverShopListPage.value?.plus(PAGE_UP)
            }.onError {
                Log.d("jomi", "네트워크로 부터 에러응답을 내려받음")
            }.onException {
                Log.d("jomi", "네트워크로 응답을 받기 전/후에 예상치 못한 이유로 요청이 실패함")
            }

        }
    }



    fun searchNaverShop(swipeRefreshLayout: SwipeRefreshLayout, query: String){
        if(searchJob.isCompleted) {
            _searchNaverShop(swipeRefreshLayout, query, _naverShopListPage.value!!)
        }
    }

    fun pageReset(){
        _naverShopListPage.value = 0
    }


    companion object{
        const val PAGE_SIZE = 20
        const val PAGE_UP = 1
    }
}

