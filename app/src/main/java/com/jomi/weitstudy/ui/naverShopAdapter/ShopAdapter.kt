package com.jomi.weitstudy.ui.naverShopAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jomi.weitstudy.databinding.ItemShopBinding
import com.jomi.weitstudy.network.model.NaverShopItem

class ShopAdapter: ListAdapter<NaverShopItem, NaverShopViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverShopViewHolder {
        return NaverShopViewHolder(ItemShopBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: NaverShopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object{
        private val DiffCallback = object: DiffUtil.ItemCallback<NaverShopItem>() {
            override fun areItemsTheSame(oldItem: NaverShopItem, newItem: NaverShopItem): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(oldItem: NaverShopItem, newItem: NaverShopItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}