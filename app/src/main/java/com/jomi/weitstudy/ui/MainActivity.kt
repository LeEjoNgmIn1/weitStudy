package com.jomi.weitstudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jomi.weitstudy.R
import com.jomi.weitstudy.databinding.ActivityMainBinding
import com.jomi.weitstudy.ui.naverShopAdapter.ShopAdapter
import dagger.hilt.android.AndroidEntryPoint

// NaverShop Branch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private var display = 0
    private val myRecyclerViewAdapter : ShopAdapter = ShopAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()

        binding.btnGet.setOnClickListener {
            initViewModel()
        }

        binding.refreshLayout.setOnRefreshListener {
            display += 10
            initViewModel()
            binding.refreshLayout.setRefreshing(false)
        }

    }
    private fun initRecycleView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = myRecyclerViewAdapter
        viewModel.naverShopResult .observe(this){
            if (it != null) {
                myRecyclerViewAdapter.submitList(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() {
        viewModel.searchNaverShop(display)
    }
}