package com.zain.fly365.base.javakoin

import android.app.Application
import com.zain.fly365.base.data.remote.di.remoteModule
import com.zain.fly365.base.di.appModule
import com.zain.fly365.base.threadsexecution.executionSchedulerModule
import com.zain.fly365.oneway.di.oneWayModule
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext

fun start(application: Application) {
    StandAloneContext.startKoin(
        listOf(
            appModule
            , executionSchedulerModule
            , remoteModule
            , oneWayModule
        )
    ) with application
}