package com.jomi.weitstudy.repository

import com.jomi.weitstudy.api.ApiHelper
import javax.inject.Inject


/*
리포지토리 패키지 내에서 응답을 얻기 위해 ApiHelper의 함수를 호출하는 MainRepository 클래스를 생성한다.
여기서도 ApiHelper 함수가 일시 중지된 함수를 반환하므로 getEmployee() 함수를 일시 중지 했다.
 */

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
    ) {
    suspend fun getNews() = apiHelper.getNews()
}