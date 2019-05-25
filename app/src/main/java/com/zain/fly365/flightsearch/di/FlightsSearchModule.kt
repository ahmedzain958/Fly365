package com.zain.fly365.flightsearch.di

import com.zain.fly365.flightsearch.data.*
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenter
import com.zain.fly365.flightsearch.presentation.presenter.AirportsListPresenterImpl
import com.zain.fly365.flightsearch.domain.*
import com.zain.fly365.flightsearch.presentation.presenter.SearchOptionsPresenter
import com.zain.fly365.flightsearch.presentation.presenter.SearchOptionsPresenterImpl
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightPresenter
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightPresenterImpl
import com.zain.fly365.oneway.presentation.presenter.OneWayFlightsView
import org.koin.dsl.module.module
import retrofit2.Retrofit


val flightsSearchModule = module {
    factory { get<Retrofit>().create(FlightsApi::class.java) }
    factory<FlightsSearchLocalDataSource> { FlightsSearchLocalDataSourceImpl(get()) }
    factory<FlightsSearchRemoteDataSource> { FlightsSearchRemoteDataSourceImpl(get()) }
    factory<FlightsSearchRepository> { FlightsSearchRepositoryImpl(get(), get()) }
    factory { GetAirportsUseCase() }
    factory { GetTravellersNumberUseCase(get()) }
    factory { GetAdultsNumberUseCase(get()) }
    factory { GetChildrenNumberUseCase(get()) }
    factory { GetInfantsNumberUseCase(get()) }
    factory { GetCabinClassUseCase(get()) }
    factory { InsertTravellerSearchOptionsUseCase(get()) }
    factory { SearchOneWayFlightsUseCase(get()) }
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
    factory<OneWayFlightPresenter> {(view: OneWayFlightsView) ->
        OneWayFlightPresenterImpl(view,
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}