package com.jomi.weitstudy.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomi.weitstudy.network.NaverShopRepository
import com.jomi.weitstudy.network.model.NaverShopItem
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val naverShopRepository: NaverShopRepository) : ViewModel() {

    private var _naverShopResult : MutableLiveData<List<NaverShopItem>> = MutableLiveData()
    val naverShopResult : LiveData<List<NaverShopItem>> get() = _naverShopResult

    private var _naverShopApiResult : MutableList<NaverShopItem> = mutableListOf()

    private var _naerShopTotalItemNum : MutableLiveData<Int> = MutableLiveData(0)

    private var _naverShopListPage : MutableLiveData<Int> = MutableLiveData(0)
    val naverShopListPage : LiveData<Int> get() = _naverShopListPage

    private fun _searchNaverShop(page : Int = 0) {
        viewModelScope.launch {

            val response = naverShopRepository.naverShopSearch("가방", PAGE_SIZE, page * PAGE_SIZE + 1)
            response.onSuccess {
                var temp = data.items
                temp?.let { _naverShopApiResult.addAll(it) }

                _naerShopTotalItemNum.postValue(data.total)
                _naverShopResult.postValue(_naverShopApiResult.toList())
                pageUp()
            }.onError {
                Log.d("jomi", "네트워크로 부터 에러응답을 내려받음")
            }.onException {
                Log.d("jomi", "네트워크로 응답을 받기 전/후에 예상치 못한 이유로 요청이 실패함")
            }
        }
    }

    fun searchNaverShop(){
        _searchNaverShop(_naverShopListPage.value!!)
    }

    private fun pageUp(){
        _naverShopListPage.value = _naverShopListPage.value?.plus(PAGE_UP)
    }

    fun hasNextPage() : Boolean {
        return _naverShopListPage.value!! * PAGE_SIZE < _naerShopTotalItemNum.value!!
    }


    companion object{
        const val PAGE_SIZE = 20
        const val PAGE_UP = 1
    }
}
