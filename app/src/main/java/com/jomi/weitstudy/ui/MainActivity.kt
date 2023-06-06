package com.jomi.weitstudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomi.weitstudy.R
import com.jomi.weitstudy.databinding.ActivityMainBinding
import com.jomi.weitstudy.ui.naverShopAdapter.ShopAdapter
import dagger.hilt.android.AndroidEntryPoint

// NaverShop Branch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val myRecyclerViewAdapter : ShopAdapter = ShopAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()
        initNaverShopViewModel()
        onScrolled()
        onRefresh()


    }
    private fun initRecycleView() {
        binding.rvNaverShopSearch.layoutManager = GridLayoutManager(this, 2)
        binding.rvNaverShopSearch.adapter = myRecyclerViewAdapter
        viewModel.naverShopResult .observe(this){
            if (it != null) {
                myRecyclerViewAdapter.submitList(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initNaverShopViewModel() {
        viewModel.searchNaverShop()
    }

    private fun onScrolled(){
        binding.rvNaverShopSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.rvNaverShopSearch.canScrollVertically(1)) {
                    initNaverShopViewModel()
                }
            }
        })
        Toast.makeText(this, "page : ${viewModel.naverShopListPage.value}", Toast.LENGTH_SHORT).show()
    }

    private fun onRefresh(){
        binding.refreshLayout.setOnRefreshListener {
            Toast.makeText(this, R.string.Refresh, Toast.LENGTH_SHORT).show()
            binding.refreshLayout.isRefreshing = false
        }
    }

}