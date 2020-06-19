package com.example.MvpExample.presenter

import android.os.Handler
import com.example.MvpExample.model.DogList

// view와 1:1 관계를 유지할 presenter
class SearchPresenter : SearchContract.Presenter {

    private var searchView: SearchContract.View? = null

    // view 와 presenter 연결
    override fun takeView(view: SearchContract.View) {
        searchView = view
    }

    override fun getDogList() {
        searchView?.showLoading()

        // 네트워크 통신시에 2초뒤에 보여주기
        Handler().postDelayed({
            val dogList = DogList.getDoglistData()
            // model에서 전달받은 데이터를 view에 전달
            searchView?.showDogList(dogList)
            // 통신끝난뒤에 progressbar 숨기기
            searchView?.hideLoading()
        }, 2000)
    }

    // view가 제거된 것을 presenter에 전달
    override fun dropView() {
        searchView = null
    }
}