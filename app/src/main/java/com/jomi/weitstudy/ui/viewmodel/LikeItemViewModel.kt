package com.jomi.weitstudy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomi.weitstudy.data.Room.LikeItemRepository
import com.jomi.weitstudy.data.model.LikeItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeItemViewModel @Inject constructor(private val likeItemRepository: LikeItemRepository) : ViewModel() {
    private var _likeItemsData : MutableLiveData<List<LikeItems>> = MutableLiveData()
    val likeItemData : LiveData<List<LikeItems>> get() = _likeItemsData

    private val _currentLikeItemData : MutableList<List<LikeItems>> = mutableListOf()

    private var _naverShopAllId : MutableList<String> = mutableListOf()

    init {
        viewModelScope.launch {
            _naverShopAllId.addAll(likeItemRepository.getAllLikeId())

            _likeItemsData.postValue(likeItemRepository.getAllLikeItemData())
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

}