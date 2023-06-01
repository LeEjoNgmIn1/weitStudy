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

    private val _naverShopResult : MutableLiveData<List<NaverShopItem>> = MutableLiveData()

    val naverShopResult : LiveData<List<NaverShopItem>> = _naverShopResult


    fun loadListData(display: Int = 10) {
        repository.makeApiCall("가방", display, 1, _naverShopResult)
    }
}