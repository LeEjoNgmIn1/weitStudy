package com.jomi.weitstudy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jomi.weitstudy.data.model.LikeItems
import com.jomi.weitstudy.databinding.ItemShopBinding

class ShopAdapter(private val isFavorite: (String) -> Boolean,
                  private val toggleclick: (LikeItems, Boolean) -> Unit
): ListAdapter<LikeItems, NaverShopViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverShopViewHolder {
        return NaverShopViewHolder(ItemShopBinding.inflate(LayoutInflater.from(parent.context),parent, false), isFavorite, toggleclick)
    }

    override fun onBindViewHolder(holder: NaverShopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object{
        private val DiffCallback = object: DiffUtil.ItemCallback<LikeItems>() {
            override fun areItemsTheSame(oldItem: LikeItems, newItem: LikeItems): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(oldItem: LikeItems, newItem: LikeItems): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class NaverShopViewHolder(private val binding: ItemShopBinding,
                          private val isFavorite: (String) -> Boolean,
                          private val toggleclick: (LikeItems, Boolean) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: LikeItems){
        binding.tvShopTitle.text = data.title
        binding.tvShopMallName.text = data.mallName
        binding.tvShopLprice.text = data.lprice
        binding.tbLike.isChecked = isFavorite(data.productId)

        binding.tbLike.setOnClickListener {
            toggleclick(
                LikeItems(
                    data.productId,
                    data.lprice,
                    data.mallName,
                    data.image,
                    data.title,
                ),
            binding.tbLike.isChecked
            )
        }


        Glide.with(binding.ivShopItemList)
            .load(data.image)
            .into(binding.ivShopItemList)
    }
}

