package com.jomi.weitstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jomi.weitstudy.databinding.ItemShopBinding
import com.jomi.weitstudy.network.NaverShopItem
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopAdapter: ListAdapter<NaverShopItem, ShopAdapter.ShopViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(ItemShopBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ShopViewHolder(private val binding: ItemShopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: NaverShopItem){
            binding.tvShopTitle.text = "상품명 : " + data.title
            binding.tvShopMallName.text = "쇼핑몰 : " + data.mallName
            binding.tvShopLprice.text = "최저가 : " + data.lprice + "원"

            Glide.with(binding.ivShopItemList)
                .load(data.image)
                .into(binding.ivShopItemList)
        }
    }

    companion object{
        private val DiffCallback = object: DiffUtil.ItemCallback<NaverShopItem>() {
            override fun areItemsTheSame(oldItem: NaverShopItem, newItem: NaverShopItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: NaverShopItem, newItem: NaverShopItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}