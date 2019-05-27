package com.zain.fly365.oneway.presentation.presenter

import com.zain.fly365.base.data.exception.APIException
import com.zain.fly365.base.threadsexecution.ExecutionThread
import com.zain.fly365.flightsearch.data.CabinClassEnum
import com.zain.fly365.flightsearch.domain.*
import com.zain.fly365.flightsearch.entities.Flight
import com.zain.fly365.flightsearch.entities.RequestLeg
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*

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
    companion object {
        const val TIME_FORMAT = "hh:mm"
        const val DATE_FORMAT = "MM/dd"
        const val DOLLAR_SIGN = "$"
    }

    override fun getFlights(leg: RequestLeg) {
        val cabinClass = getCabinClass().name
        val infant = getInfantsNumber()
        val child = getChildrenNumber()
        val adult = getAdultsNumber()
        val legs = mutableListOf<RequestLeg>()
        legs.add(leg)
        var flights = mutableListOf<Flight>()
        compositeDisposable.add(
            searchOneWayFlightsUseCase.execute(
                SearchOneWayFlightsUseCase.Params.create(
                    cabinClass, infant, child, adult, legs
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
                .subscribe({ response ->
                    response.itineraries.forEach { itinerary ->
                        //todo convert to rxjava
                        val flight = Flight()
                        flight.price = itinerary.displayPricing.total.toString() + DOLLAR_SIGN
                        response.legs.forEach { leg ->
                            if (leg.legId == itinerary.legs.get(0)) {
                                flight.airway = leg.carrier.name
                                flight.departureTime = getFormattedDate(leg.departureDate, TIME_FORMAT)
                                flight.date = getFormattedDate(leg.departureDate, DATE_FORMAT)
                                flight.arrivalTime = getFormattedDate(leg.arrivalDate, TIME_FORMAT)
                                flight.originAirport = leg.origin
                                flight.destinationAirport = leg.destination
                                flight.numberOfStops = leg.stops
                                flight.duration = formatHoursAndMinutes(leg.duration)
                            }
                        }
                        flights.add(flight)
                    }
                    view?.fillFlightsList(flights)
                }, { throwable ->
                    if (throwable is APIException)
                        view?.showError(throwable.message!!)
                    else {//for handling exceptions that no need to be casted to APIException like (No internet Connection...
                        view?.showError(throwable.message!!)
                    }
                })
        )
    }

    fun getCabinClass(): CabinClassEnum {
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

    fun getFormattedDate(date: String, format: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val datez = inputFormat.parse(date.dropLast(6))
        return outputFormat.format(datez)
    }

    fun formatHoursAndMinutes(totalMinutes: Int): String {
        var minutes = Integer.toString(totalMinutes % 60)
        minutes = if (minutes.length == 1) "0$minutes" else minutes
        return (totalMinutes / 60).toString() + "h " + minutes + "m"
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable
        view = null
    }
}