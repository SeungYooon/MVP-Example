package com.example.rxjava.presenter

import com.example.rxjava.base.BasePresenter
import com.example.rxjava.base.BaseView
import com.example.rxjava.model.Dog

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