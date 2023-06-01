package com.jomi.weitstudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jomi.weitstudy.R
import com.jomi.weitstudy.databinding.ActivityMainBinding
import com.jomi.weitstudy.others.Status
import kotlinx.android.synthetic.main.activity_main.*

// Hilt_Retrofit_Coroutine Branch
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter()
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapter

        mainViewModel.res.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    rvNews.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                    rvNews.visibility = View.GONE
                }

                Status.ERROR -> {
                    progress.visibility = View.GONE
                    rvNews.visibility =View.VISIBLE
                    Snackbar.make(rootView, "Somthing went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

    }
}