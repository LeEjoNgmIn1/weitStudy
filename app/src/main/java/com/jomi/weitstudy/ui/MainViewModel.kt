package com.jomi.weitstudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jomi.weitstudy.network.RetroRepository
import com.jomi.weitstudy.network.model.NaverShopItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetroRepository ) : ViewModel() {

    private val naverShopList : MutableLiveData<List<NaverShopItem>> = MutableLiveData()

    val naverShopLiveData : LiveData<List<NaverShopItem>> = naverShopList


    fun loadListData() {
        repository.makeApiCall("가방", 10, 1, naverShopList)
    }
}