package com.jomi.weitstudy.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jomi.weitstudy.data.model.LikeItems
import com.jomi.weitstudy.databinding.ActivityLikeItemBinding
import com.jomi.weitstudy.ui.adapter.ShopAdapter
import com.jomi.weitstudy.ui.viewmodel.LikeItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeItemActivity : AppCompatActivity() {

    lateinit var binding: ActivityLikeItemBinding
    private var likeRecyclerViewAdapter: ShopAdapter = ShopAdapter({
            itemId : String -> viewModel.isLike(itemId)
    },{
            likeItems: LikeItems, isCheck: Boolean -> viewModel.updateLikeItem(likeItems, isCheck)
    })

    private val viewModel: LikeItemViewModel by lazy {
        ViewModelProvider(this).get(LikeItemViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikeItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.title = "MY 좋아요"

        setSupportActionBar(binding.topAppBar)

        setUpLikeItemRecycerlView()

    }

    private fun setUpLikeItemRecycerlView(){
        binding.rvLikeItem.layoutManager = GridLayoutManager(this, 2)
        binding.rvLikeItem.adapter = likeRecyclerViewAdapter

        viewModel.likeItemData.observe(this){
            if (it != null){
                likeRecyclerViewAdapter.submitList(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

