package com.jomi.weitstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jomi.weitstudy.network.Item
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopAdapter: ListAdapter<Item, ShopAdapter.ShopViewHolder>(DiffCallback) {

    private var listData: List<Item>? = null

    fun setlistData(listData: List<Item>?){
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        return listData?.size!!
    }

    class ShopViewHolder(view : View): RecyclerView.ViewHolder(view){
        val itemTitle = view.tvShopTitle
        val itemMalName = view.tvShopMallName
        val itemLprice = view.tvShopLprice
        val itemImage = view.ivShopItemList

        fun bind(data: Item){
            itemTitle.text = "상품명 : " + data.title
            itemMalName.text = "쇼핑몰 : " + data.mallName
            itemLprice.text = "최저가 : " + data.lprice + "원"

            Glide.with(itemImage)
                .load(data.image)
                .into(itemImage)
        }
    }

    companion object{
        private val DiffCallback = object: DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}