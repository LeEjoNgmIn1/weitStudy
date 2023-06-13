package com.jomi.weitstudy.others

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// EditText에 대한 확장
fun EditText.onMyTextChanged(completion: (Editable?) -> Unit){
    this.addTextChangedListener(object : TextWatcher {

        // 입력이 끝났을 때 조치
        override fun afterTextChanged(editable : Editable?) {
            completion(editable)
        }
        // 입력하기 전에 조치
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        // 입력난에 변화가 있을 시 조치
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}