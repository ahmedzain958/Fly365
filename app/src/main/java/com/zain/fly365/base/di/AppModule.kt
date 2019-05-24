package com.zain.fly365.base.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module.module

val appModule = module {
    factory { CompositeDisposable() }
}