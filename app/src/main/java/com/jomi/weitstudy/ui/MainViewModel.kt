package com.jomi.weitstudy.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
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

    private var _naverShopListPage : MutableLiveData<Int> = MutableLiveData(0)
    val naverShopListPage : LiveData<Int> get() = _naverShopListPage

    fun searchNaverShop(page : Int) {
        viewModelScope.launch {
            val response = naverShopRepository.naverShopSearch("가방", PAGE_SIZE, page * PAGE_SIZE + 1)
            _naverShopResult.postValue(response.body()?.items)
        }
    }

    @SuppressLint("LogNotTimber")
    fun pageUp(){
        _naverShopListPage.value = _naverShopListPage.value?.plus(1)
    }

    companion object{
        const val PAGE_SIZE = 20
    }
}
