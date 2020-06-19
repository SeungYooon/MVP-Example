package com.example.MvpExample.presenter

import com.example.MvpExample.base.BasePresenter
import com.example.MvpExample.base.BaseView
import com.example.MvpExample.model.Dog

// view, presenter가 구현해야할 interface를 정의하는 contract
interface SearchContract {
    interface View : BaseView {
        // show progressBar
        fun showLoading()
        // hide progressBar
        fun hideLoading()
        fun showDogList(dogList: List<Dog>)
    }

    interface Presenter : BasePresenter<View> {
        // model로 부터 데이터를 받아오기위한 함수
        fun getDogList()
    }
}