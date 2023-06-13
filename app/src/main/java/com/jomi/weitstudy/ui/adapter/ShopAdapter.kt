package com.jomi.weitstudy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

class NaverShopViewHolder(private val binding: ItemShopBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: NaverShopItem){
        binding.tvShopTitle.text = data.title
        binding.tvShopMallName.text = data.mallName
        binding.tvShopLprice.text = data.lprice

        Glide.with(binding.ivShopItemList)
            .load(data.image)
            .into(binding.ivShopItemList)
    }
}

