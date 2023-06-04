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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val naverShopRepository: NaverShopRepository) : ViewModel() {

    private var _naverShopResult : MutableLiveData<List<NaverShopItem>> = MutableLiveData()
    val naverShopResult : LiveData<List<NaverShopItem>> get() = _naverShopResult

    private var _naverShopApiResult : MutableList<NaverShopItem> = mutableListOf()

    private var _naverShopListPage : MutableLiveData<Int> = MutableLiveData(0)

    private fun _searchNaverShop(page : Int = 0) {
        viewModelScope.launch {
            val response = naverShopRepository.naverShopSearch("가방", PAGE_SIZE, page * PAGE_SIZE + 1)
            var temp = response.body()?.items
            temp?.let { _naverShopApiResult.addAll(it) }

            _naverShopResult.postValue(_naverShopApiResult.toList())
        }
    }

    fun searchNaverShop(){
        _searchNaverShop(_naverShopListPage.value!!)
        pageUp()
    }

    private fun pageUp(){
        _naverShopListPage.value = _naverShopListPage.value?.plus(PAGE_UP)
    }


    companion object{
        const val PAGE_SIZE = 20
        const val PAGE_UP = 1
    }
}
