package com.jomi.weitstudy.ui.naverShopAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jomi.weitstudy.databinding.ItemShopBinding
import com.jomi.weitstudy.network.model.NaverShopItem

class NaverShopViewHolder(private val binding: ItemShopBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: NaverShopItem){
        binding.tvShopTitle.text = data.title
        binding.tvShopMallName.text = data.mallName
        binding.tvShopLprice.text = data.lprice + "원"

        Glide.with(binding.ivShopItemList)
            .load(data.image)
            .into(binding.ivShopItemList)
    }
}