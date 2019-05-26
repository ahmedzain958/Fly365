package com.zain.fly365.oneway.presentation.presenter

import com.zain.fly365.base.data.exception.APIException
import com.zain.fly365.base.threadsexecution.ExecutionThread
import com.zain.fly365.flightsearch.data.CabinClass
import com.zain.fly365.flightsearch.domain.*
import com.zain.fly365.flightsearch.entities.RequestLeg
import io.reactivex.disposables.CompositeDisposable

class OneWayFlightPresenterImpl(
    private var view: OneWayFlightsView?,
    private val searchOneWayFlightsUseCase: SearchOneWayFlightsUseCase,
    val getAdultsNumberUseCase: GetAdultsNumberUseCase,
    val getChildrenNumberUseCase: GetChildrenNumberUseCase,
    val getInfantsNumberUseCase: GetInfantsNumberUseCase,
    val getCabinClassUseCase: GetCabinClassUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val executionThread: ExecutionThread
) : OneWayFlightPresenter {
    override fun getFlights(leg: RequestLeg) {
        val cabinClass = getCabinClass().name
        val infant = getInfantsNumber()
        val child = getChildrenNumber()
        val adult = getAdultsNumber()
        val legs = mutableListOf<RequestLeg>()
        legs.add(leg)
        compositeDisposable.add(
            searchOneWayFlightsUseCase.execute(
                SearchOneWayFlightsUseCase.Params.create(
                    cabinClass,
                    infant,
                    child,
                    adult,
                    legs
                )
            )
                .subscribeOn(executionThread.getIOThread())
                .observeOn(executionThread.getMainThread())
                .doOnSubscribe {
                    view?.showLoading()
                }
                .doFinally {
                    view?.hideLoading()
                }
                .subscribe({
                    it?.let { response ->

                    }
                }, { throwable ->
                    if (throwable is APIException) {
                        view?.showError(throwable.message!!)
                    } else {
                        view?.showError(throwable.message!!)
                    }
                })
        )
    }

    fun getCabinClass(): CabinClass {
        return getCabinClassUseCase.execute(null)
    }

    fun getAdultsNumber(): Int {
        return getAdultsNumberUseCase.execute(null)
    }

    fun getChildrenNumber(): Int {
        return getChildrenNumberUseCase.execute(null)
    }

    fun getInfantsNumber(): Int {
        return getInfantsNumberUseCase.execute(null)
    }

    override fun onDestroy() {
        view = null
    }
}