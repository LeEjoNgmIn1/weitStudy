package com.jomi.weitstudy.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomi.weitstudy.network.NaverShopRepository
import com.jomi.weitstudy.network.model.NaverShopItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val naverShopRepository: NaverShopRepository) : ViewModel() {

    private val _naverShopResult : MutableLiveData<List<NaverShopItem>> = MutableLiveData()
    val naverShopResult : LiveData<List<NaverShopItem>> get() = _naverShopResult


    fun searchNaverShop(display: Int) = viewModelScope.launch{
        val response = naverShopRepository.naverShopSearch("가방", display, 1)
        if(response.isSuccessful){
            response.body()?.items?.let { body ->
                _naverShopResult.postValue(body as List<NaverShopItem>?)
            }
        }
    }
}