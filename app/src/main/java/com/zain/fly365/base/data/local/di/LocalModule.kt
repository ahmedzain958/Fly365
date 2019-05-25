package com.zain.fly365.base.data.local.di

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import com.zain.fly365.base.data.local.AppPreference
import com.zain.fly365.base.data.local.LocalConstants.PREFERENCE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

@SuppressLint("CommitPrefEdits")
@JvmField
val localModule = module {
    single {
        getSharedPrefs(androidApplication())
    }
    single<SharedPreferences.Editor> { getSharedPrefs(androidApplication()).edit() }
    single { AppPreference(get()) }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(PREFERENCE_NAME, android.content.Context.MODE_PRIVATE)
}