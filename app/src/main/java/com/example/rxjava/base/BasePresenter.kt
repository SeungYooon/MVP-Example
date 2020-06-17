package com.example.rxjava.base

interface BasePresenter<T> {
    // view 생성 또는 bind 될때 presenter에게 전달
    fun takeView(view: T)
    // view 제거 또는 unbind 될때 presenter에게 전달
    fun dropView()
}