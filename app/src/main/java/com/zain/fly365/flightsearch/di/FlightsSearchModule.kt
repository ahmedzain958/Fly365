package com.zain.fly365.flightsearch.di

import com.zain.fly365.flightsearch.data.FlightsSearchLocalDataSource
import com.zain.fly365.flightsearch.data.FlightsSearchLocalDataSourceImpl
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenter
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenterImpl
import com.zain.fly365.flightsearch.data.FlightsSearchRepositoryImpl
import com.zain.fly365.flightsearch.domain.*
import com.zain.fly365.flightsearch.presentation.presenter.SearchOptionsPresenter
import com.zain.fly365.flightsearch.presentation.presenter.SearchOptionsPresenterImpl
import org.koin.dsl.module.module


val flightsSearchModule = module {
    factory<FlightsSearchLocalDataSource> { FlightsSearchLocalDataSourceImpl(get()) }
    factory<FlightsSearchRepository> { FlightsSearchRepositoryImpl(get()) }
    factory { GetAirportsUseCase() }
    factory { GetTravellersNumberUseCase(get()) }
    factory { GetAdultsNumberUseCase(get()) }
    factory { GetChildrenNumberUseCase(get()) }
    factory { GetInfantsNumberUseCase(get()) }
    factory { GetCabinClassUseCase(get()) }
    factory { InsertTravellerSearchOptionsUseCase(get()) }
    factory<SearchOptionsPresenter> {
        SearchOptionsPresenterImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    factory<AirportsListPresenter> {
        AirportsListPresenterImpl(
            get()
        )
    }
}