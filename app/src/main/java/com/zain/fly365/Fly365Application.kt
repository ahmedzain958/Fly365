package com.zain.fly365

import android.app.Application
import com.zain.fly365.base.javakoin.start

class Fly365Application:Application() {
    override fun onCreate() {
        super.onCreate()
        start(this)
    }
}