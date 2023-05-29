package com.jomi.weitstudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jomi.weitstudy.network.NaverShopItem
import com.jomi.weitstudy.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetroRepository ) : ViewModel() {

    private val naverShopList : MutableLiveData<List<NaverShopItem>> = MutableLiveData()

    val naverShopLiveData : LiveData<List<NaverShopItem>> = naverShopList


    fun loadListData() {
        repository.makeApiCall(naverShopList)
    }
}