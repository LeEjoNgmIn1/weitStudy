package com.jomi.weitstudy.others

/*
9단계 : 유틸리티 파일 설정
이제 다른 패키지 내부의 데이터를 검색하기 위해 일부 유틸리티 파일을 성성한다.

일반적인 방식으로 상용할 데이터를 UI에 래핑하는데 사용된다.
 */

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String?
){
    companion object{

        fun <T> success(data:T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg:String, data:T?): Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data:T?): Resource<T>{
            return Resource(Status.LOADING, data, null)
        }

    }
}