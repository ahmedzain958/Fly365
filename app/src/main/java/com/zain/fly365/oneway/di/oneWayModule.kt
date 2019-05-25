package com.zain.fly365.oneway.di

import com.zain.fly365.oneway.data.OneWayLocalDataSource
import com.zain.fly365.oneway.data.OneWayLocalDataSourceImpl
import com.zain.fly365.oneway.data.OneWayRepositoryImpl
import com.zain.fly365.oneway.domain.GetAirportsUseCase
import com.zain.fly365.oneway.domain.OneWayRepository
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenter
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenterImpl
import org.koin.dsl.module.module


val oneWayModule = module {
    factory<OneWayLocalDataSource> { OneWayLocalDataSourceImpl() }
    factory<OneWayRepository> { OneWayRepositoryImpl(get()) }
    factory { GetAirportsUseCase(get()) }
    factory<AirportsListPresenter> {
        AirportsListPresenterImpl(
            get()
        )
    }
}