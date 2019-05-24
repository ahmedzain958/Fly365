package com.zain.fly365.base.threadsexecution

import io.reactivex.Scheduler

interface ExecutionThread {
    fun getIOThread(): Scheduler
    fun getMainThread(): Scheduler
    fun getNewThread(): Scheduler
}