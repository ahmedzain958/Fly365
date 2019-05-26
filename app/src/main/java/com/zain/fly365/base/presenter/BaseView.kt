package com.zain.fly365.base.presenter

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(error: String)
    fun showMessage(msg: String)
}