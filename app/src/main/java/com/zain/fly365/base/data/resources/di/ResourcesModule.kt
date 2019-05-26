package com.zain.fly365.base.data.resources.di

import com.zain.fly365.base.data.resources.AppResources
import com.zain.fly365.base.data.resources.repository.ResourcesRepository
import org.koin.dsl.module.module

val resourcesModule = module {

    single { AppResources(get()) }
    single { ResourcesRepository(get()) }
}