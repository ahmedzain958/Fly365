package com.zain.fly365.base.presenter

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(error: Throwable)
    fun showMessage(msg: String)
}