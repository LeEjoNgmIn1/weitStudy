package com.jomi.weitstudy.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jomi.weitstudy.R
import com.jomi.weitstudy.databinding.ActivitySearchResultBinding
import com.jomi.weitstudy.ui.viewmodel.SearchResultViewModel
import com.jomi.weitstudy.ui.adapter.ShopAdapter
import dagger.hilt.android.AndroidEntryPoint

// NaverShop Branch
@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchResultBinding

    private val myRecyclerViewAdapter: ShopAdapter = ShopAdapter()
    private val viewModel: SearchResultViewModel by lazy {
        ViewModelProvider(this).get(SearchResultViewModel::class.java)
    }

    lateinit var userQuery : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userQuery = intent.getStringExtra("inputQuery")!!
        binding.topAppBar.title = userQuery

        // 액티비티에서 어떤 액션바를 사용할 지 설정
        setSupportActionBar(binding.topAppBar)

        setUpNaverShopRecyclerView()
        pageUpNaverShopViewModel(userQuery)
        onRefresh()
    }

    private fun setUpNaverShopRecyclerView() {
        binding.rvNaverShopSearch.layoutManager = GridLayoutManager(this, 2)
        binding.rvNaverShopSearch.adapter = myRecyclerViewAdapter

        binding.rvNaverShopSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount =recyclerView.adapter!!.itemCount - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    pageUpNaverShopViewModel(userQuery)
                    Toast.makeText(this@SearchResultActivity, "page : ${viewModel.naverShopListPage.value}" , Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.naverShopResult.observe(this) {
            if (it != null) {
                myRecyclerViewAdapter.submitList(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pageUpNaverShopViewModel(query: String) {
        viewModel.searchNaverShop(query)
    }

    fun onRefresh(){
    }

}