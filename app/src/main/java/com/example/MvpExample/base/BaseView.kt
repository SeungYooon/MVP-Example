package com.example.MvpExample.base

interface BaseView {
    // 공통적으로 error 출력하는 함수
    fun showError(error: String)
}