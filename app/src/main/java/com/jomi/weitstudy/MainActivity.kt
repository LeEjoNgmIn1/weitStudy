package com.jomi.weitstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

// NaverShop Branch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerViewAdapter : ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener {
            initRecycleView()
            initViewModel()
        }

    }
    private fun initRecycleView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        myRecyclerViewAdapter = ShopAdapter()
        recycler_view.adapter = myRecyclerViewAdapter
    }

    private fun initViewModel(){
        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer{

            if(it != null){
                myRecyclerViewAdapter.setlistData(it)
                myRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.loadListData()
    }
}