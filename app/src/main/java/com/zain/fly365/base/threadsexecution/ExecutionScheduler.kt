package com.zain.fly365.base.threadsexecution

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExecutionScheduler : ExecutionThread {
    override fun getIOThread(): Scheduler = Schedulers.io()

    override fun getMainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun getNewThread(): Scheduler = Schedulers.newThread()
}
