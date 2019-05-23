package com.zain.fly365.base.ui

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


abstract class BaseActivity : AppCompatActivity() {

    protected fun initToolbar(
        toolbar: Toolbar,
        title: String,
        hasBackButton: Boolean = false
    ) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(hasBackButton)
        supportActionBar!!.setDisplayHomeAsUpEnabled(hasBackButton)
        supportActionBar!!.title = title
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideSoftKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}