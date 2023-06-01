package com.jomi.weitstudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jomi.weitstudy.R
import com.jomi.weitstudy.ui.naverShopAdapter.ShopAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

// NaverShop Branch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var display = 0
    private val myRecyclerViewAdapter : ShopAdapter = ShopAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()

        btn_get.setOnClickListener {
            initViewModel()
        }

        refresh_layout.setOnRefreshListener {
            display += 10
            initViewModel()
        }

    }
    private fun initRecycleView() {
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.adapter = myRecyclerViewAdapter
        viewModel.naverShopResult .observe(this){
            if (it != null) {
                myRecyclerViewAdapter.submitList(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() {
        
    }
}