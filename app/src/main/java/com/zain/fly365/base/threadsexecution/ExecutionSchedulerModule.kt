package com.zain.fly365.base.threadsexecution

import org.koin.dsl.module.module


val executionSchedulerModule = module {
    factory { ExecutionScheduler() } bind ExecutionThread::class
}