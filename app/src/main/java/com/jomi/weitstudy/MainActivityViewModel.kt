package com.jomi.weitstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jomi.weitstudy.network.Item
import com.jomi.weitstudy.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository ) : ViewModel() {

    lateinit var liveDataList : MutableLiveData<List<Item>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver() : MutableLiveData<List<Item>> {
        return liveDataList
    }

    fun loadListData() {
        repository.makeApiCall(liveDataList)
    }
}