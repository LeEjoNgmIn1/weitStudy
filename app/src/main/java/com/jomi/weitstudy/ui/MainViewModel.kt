package com.jomi.weitstudy.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomi.weitstudy.models.NewsResponse
import com.jomi.weitstudy.others.Resource
import com.jomi.weitstudy.repository.MainRepository
import kotlinx.coroutines.launch

/*
10단계 : UI 구현
다음과 같이 ViewModel 클래스를 확장해야 하는 ui 패키지 내에 MainViewModel 클래스를 생성하기 위한 UI를 구현해야한다.

여기에서는 Hilt가 내부적으로 뷰모데일에 주입을 제공하므로 생성자에 "@Inject" 대신 "@ViewModelInject"를 사용 했다. 따라서 다른 인스턴스를
생성하지 않고 여기 생성자에서 mainRepository를 전달 했다. 여기서 볼 수 있듯이 getEmployess() 함수는 위에서 정의한 Resource 클래스 내부의
응답을 래핑한다.

이제 뉴스 정보를 표시하기 위해 recyclerview의 어뎁터를 생성한다.

 */


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel(){
    private val _res = MutableLiveData<Resource<NewsResponse>>()

    val res : LiveData<Resource<NewsResponse>>
    get() = _res

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            _res.postValue(Resource.loading(null))
            mainRepository.getNews().let {
                if(it.isSuccessful){
                    _res.postValue(Resource.success(it.body()))
                } else {
                    _res.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}
