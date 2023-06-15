package com.jomi.weitstudy.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jomi.weitstudy.databinding.ActivityMainBinding
import com.jomi.weitstudy.others.onMyTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var inputQuery : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 텍스트가 변경되었을떄
        binding.etMainSearch.onMyTextChanged {
            // 글자가 입력 되면
            if(it.toString().isNotEmpty()){
                // helperText 삭제
                binding.tfMainSearch.helperText = ""
                // 스크롤뷰를 올리기
                binding.svMain.scrollTo(0, 200)
            }
            // 입력된 글자가 12글자 이상일떄 토스트 띄우기
            if(it.toString().count() == 12){
                Toast.makeText(this, "검색어는 12자까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 검색 버튼 클릭스
        binding.btnItemSearch.setOnClickListener {
            val intent : Intent = Intent(this, SearchResultActivity::class.java)
            inputQuery = binding.etMainSearch.text.toString()

            if (inputQuery.isEmpty()){
                Toast.makeText(this, "검색어를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                intent.putExtra("inputQuery", inputQuery)
                startActivity(intent)
            }

        }
    }
}