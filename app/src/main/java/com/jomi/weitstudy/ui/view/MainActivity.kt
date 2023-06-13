package com.jomi.weitstudy.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jomi.weitstudy.R
import com.jomi.weitstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}